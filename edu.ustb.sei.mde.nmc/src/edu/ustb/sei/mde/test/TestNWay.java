package edu.ustb.sei.mde.test;

import org.apache.commons.collections4.map.MultiKeyMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.sun.net.httpserver.Filter;

import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.IComparisonScope;
import edu.ustb.sei.mde.compare.IMatchEngine;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.internal.ComparisonSpec;
import edu.ustb.sei.mde.compare.internal.MatchSpec;
import edu.ustb.sei.mde.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.compare.start.DefaultComparisonScope;
import edu.ustb.sei.mde.compare.start.EMFCompare;
import edu.ustb.sei.mde.compare.start.MatchEngineFactoryImpl;
import edu.ustb.sei.mde.compare.start.MatchEngineFactoryRegistryImpl;
import edu.ustb.sei.mde.nway.MaximalCliquesWithPivot;

public class TestNWay {

	public static void main(String[] args) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		URI baseURI = URI.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\bank.ecore");
		URI branch1URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\bank1.ecore");
		URI branch2URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\bank2.ecore");
		URI branch3URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\bank3.ecore");

		List<URI> uriList = new ArrayList<>();
		uriList.add(baseURI);
		uriList.add(branch1URI);
		uriList.add(branch2URI);
		uriList.add(branch3URI);

		int size = uriList.size();
		List<Resource> resourceList = new ArrayList<>(size);
		Map<Resource, Integer> resourceMap = new HashMap<>();
		for (int i = 0; i < size; i++) {
			Resource resource = resourceSet.getResource(uriList.get(i), true);
			resourceList.add(resource);
			resourceMap.put(resource, i); // Ϊ�˷����¼�¼�Ԫ�������ĸ���֧ģ��
		}

		// never use identifiers
		IMatchEngine.Factory.Registry registry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
		final MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl(UseIdentifiers.NEVER);
		matchEngineFactory.setRanking(20);
		registry.add(matchEngineFactory);

		// ֻʹ��һ��EMFCompare
		EMFCompare build = EMFCompare.builder().setMatchEngineFactoryRegistry(registry).build();

		Comparison comparison = null;
		IComparisonScope scope = null;
		Resource baseResource = resourceList.get(0);
		Resource branchResource = null;

		// ��ԭʼģ���������֧ģ�ͽ��ж���ƥ��
		// ƥ����ͬһ��ԭʼԪ�صģ����ֵ���ͬ��ƥ����
		// ��Ҫ��¼�¼ӵ�Ԫ��
		// ��Ҫ��¼Ԥƥ����Ϣ
		Map<EObject, List<EObject>> matchGroupMap = new HashMap<>();
		Map<Integer, List<EObject>> addMap = new HashMap<>();
		// ��Ҫ��¼���¼ӵ�ƥ���飬����baseEObjectû��value��
		Map<EObject, List<EObject>> nodesIndexMap = new HashMap<>();

		for (int i = 1; i < size; i++) { // resourceList�±�Ϊ0ʱ����ӦbaseResource
			branchResource = resourceList.get(i);
			scope = new DefaultComparisonScope(baseResource, branchResource, null);
			long start = System.currentTimeMillis();
			comparison = build.compare(scope);
			long end = System.currentTimeMillis();
			System.out.println("MATCH TIME: " + (end - start) + " ms.\n");

			for (Match match : comparison.getMatches()) {
				EObject baseEObject = match.getLeft();
				EObject branchEObject = match.getRight();
				if (baseEObject != null) {
					List<EObject> list = matchGroupMap.get(baseEObject);
					if (list == null) {
						list = new ArrayList<>();
						matchGroupMap.put(baseEObject, list);
					}
					list.add(branchEObject);

					// need to improve?
					if (branchEObject != null) {
						nodesIndexMap.put(branchEObject, list);
					}

				} else {
					List<EObject> addList = addMap.get(i);
					if (addList == null) {
						addList = new ArrayList<>();
						addMap.put(i, addList); // ����ͬ��֧ģ�͵������¼�Ԫ�طŵ�һ��
					}
					addList.add(branchEObject);
				}
			}

		}

		// Ԥƥ����Ϣ������֧i����֧j��Ϊ��
		MultiKeyMap<Integer, List<Match>> preMatchMap = new MultiKeyMap<>();
		matchGroupMap.values().forEach(value -> {
			for (int i = 1; i < size - 1; i++) {
				EObject leftEObject = value.get(i - 1); // ע�⣺value�±�Ϊ0ʱ����Ӧ���Ƿ�֧1
				for (int j = i + 1; j < size; j++) {
					EObject rightEObject = value.get(j - 1);
					if (leftEObject != null || rightEObject != null) {
						List<Match> list = preMatchMap.get(i, j);
						if (list == null) {
							list = new ArrayList<>();
							preMatchMap.put(i, j, list);
						}
						Match match = new MatchSpec();
						match.setLeft(leftEObject);
						match.setRight(rightEObject);
						list.add(match);
					}
				}
			}
		});

		// �����¼�Ԫ�أ������ȽϷ�֧ģ��
		Set<EObject> vertices = new HashSet<>();
		Set<Match> edges = new HashSet<>();
		// ��Ҫ��¼�༭����
		MultiKeyMap<EObject, Double> distanceMap = new MultiKeyMap<>();

		for (int i = 1; i < size - 1; i++) {
			List<EObject> leftEObjects = addMap.get(i);
			if (leftEObjects != null) {
				vertices.addAll(leftEObjects);
				for (int j = i + 1; j < size; j++) {
					List<EObject> rightEObjects = addMap.get(j);
					if (rightEObjects != null) {
						vertices.addAll(rightEObjects);
						// ����Ԥƥ����Ϣ
						Comparison comparisonADD = new ComparisonSpec();
						List<Match> preMatchList = preMatchMap.get(i, j);
						comparisonADD.getMatches().addAll(preMatchList);

						long start = System.currentTimeMillis();
						build.compareADD(comparisonADD, leftEObjects, rightEObjects, distanceMap);
						long end = System.currentTimeMillis();
						System.out.println("\n\n\nADD MATCH TIME: " + (end - start) + " ms.");

						// ���˳��¼�Ԫ�ص�ƥ��
						int num = preMatchList.size();
						List<Match> collect = comparisonADD.getMatches().stream().skip(num)
								.filter(m -> m.getLeft() != null && m.getRight() != null).collect(Collectors.toList());
						edges.addAll(collect);
					}
				}
			}
		}

		// tmp
		System.out.println("\n\n\n----------------------edges");
		edges.forEach(e -> {
			System.out.println(e.getLeft());
			System.out.println(e.getRight());
			System.out.println();
		});
		System.out.println("\n\n\n");

		// ���š�����ѡ��
		List<List<EObject>> matchGroupList = new ArrayList<>();

		MaximalCliquesWithPivot ff = new MaximalCliquesWithPivot();
		ff.initGraph(vertices, edges);
		List<List<EObject>> maximalCliques = new ArrayList<>();
		ff.Bron_KerboschPivotExecute(maximalCliques);

		// tmp
		System.out.println("\n\n\n----------------------maximalCliques");
		maximalCliques.forEach(m -> {
			m.forEach(e -> {
				System.out.println("e: " + e);
			});
			System.out.println();
		});
		System.out.println("\n\n\n");

		while (maximalCliques.size() > 0) {

			Map<List<EObject>, Double> map = new HashMap<>();

			for (List<EObject> clique : maximalCliques) {
				// ɸѡ��containerΪ�գ�����containerλ��ͬһ��ƥ�����
				if (checkValid(clique, nodesIndexMap)) {
					int sum = 0;
					for (int i = 0; i < clique.size() - 1; i++) {
						EObject eObjectI = clique.get(i);
						for (int j = i + 1; j < clique.size(); j++) {
							EObject eObjectJ = clique.get(j);
							Double distance = distanceMap.get(eObjectI, eObjectJ);
							if (distance == null) {
								distance = distanceMap.get(eObjectJ, eObjectI);
							}
							sum += distance;
						}
					}

					if (sum == 0) {
						sum = 1; // sum��Ϊ0�������ڵ���5��Ϊ��֮��ĳ���
					}
					int cSize = clique.size();
					if (clique.size() == 1) {
						cSize = 0;	// ����ֻ��һ��Ԫ��ʱ����cSizeΪ0������ѡ���������ȼ����
					}

					map.put(clique, Double.valueOf(cSize) / sum);
				}
			}

			// tmp
			map.forEach((key, value) -> {
				key.forEach(e -> {
					System.out.println(e);
				});

				System.out.println(value);
				System.out.println();
			});

			// ����value�������򣨽��򣩣�ȡ��һ����Ϊƥ����
			Optional<Entry<List<EObject>, Double>> findFirst = map.entrySet().stream()
					.sorted(Map.Entry.<List<EObject>, Double>comparingByValue().reversed()).findFirst();
			List<EObject> matchGroup = findFirst.get().getKey();
			matchGroupList.add(matchGroup);
			maximalCliques.remove(matchGroup);

			// ����nodesIndexMap��need to improve?
			matchGroup.forEach(e -> {
				nodesIndexMap.put(e, matchGroup);
			});

			// tmp
			System.out.println("\n\n\n**************��ѡ����***************��");
			matchGroup.forEach(e -> {
				System.out.println(e);
			});
			System.out.println("\n\n\n");

			// �������Ű���ƥ�����е�EObject����clique.remove(EObejct)
			// ��clique�������κζ�����maximalCliques.remove(clique)
			for (EObject eObject : matchGroup) {
				Iterator<List<EObject>> iterator = maximalCliques.iterator();
				while (iterator.hasNext()) {
					List<EObject> clique = iterator.next();
					if (clique.contains(eObject)) {
						clique.remove(eObject);
						if (clique.size() == 0) {
							iterator.remove();
						}
					}
				}
			}
		}

		// tmp
		System.out.println("\n\n\nmatchGroupList: ");
		matchGroupList.forEach(list -> {
			list.forEach(e -> {
				System.out.println(e);
			});
			System.out.println();
		});
		System.out.println("\n\n\n: ");
		
		
		
		
		
		

	}

	/**
	 * ѡ����ʱ����ѡcontainerΪ�գ�����containerλ��ͬһ��ƥ����ġ�
	 */
	private static boolean checkValid(List<EObject> clique, Map<EObject, List<EObject>> nodesIndexMap) {
		List<EObject> origin = nodesIndexMap.get(clique.get(0).eContainer());
		for (EObject e : clique) {
			EObject eContainer = e.eContainer();
			List<EObject> current = nodesIndexMap.get(eContainer);
			if (eContainer == null || (current != null && origin != null && current == origin)) {
				current = origin;
			} else {
				return false;
			}

		}
		return true;
	}

}
