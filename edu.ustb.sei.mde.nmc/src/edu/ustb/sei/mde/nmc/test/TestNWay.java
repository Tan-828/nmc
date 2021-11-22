package edu.ustb.sei.mde.nmc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import edu.ustb.sei.mde.nmc.compare.Comparison;
import edu.ustb.sei.mde.nmc.compare.IComparisonScope;
import edu.ustb.sei.mde.nmc.compare.IMatchEngine;
import edu.ustb.sei.mde.nmc.compare.Match;
import edu.ustb.sei.mde.nmc.compare.internal.ComparisonSpec;
import edu.ustb.sei.mde.nmc.compare.internal.MatchSpec;
import edu.ustb.sei.mde.nmc.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.nmc.compare.start.DefaultComparisonScope;
import edu.ustb.sei.mde.nmc.compare.start.EMFCompare;
import edu.ustb.sei.mde.nmc.compare.start.MatchEngineFactoryImpl;
import edu.ustb.sei.mde.nmc.compare.start.MatchEngineFactoryRegistryImpl;
import edu.ustb.sei.mde.nmc.nway.Conflict;
import edu.ustb.sei.mde.nmc.nway.ConflictKind;
import edu.ustb.sei.mde.nmc.nway.MaximalCliquesWithPivot;
import edu.ustb.sei.mde.nmc.nway.RefEdge;
import edu.ustb.sei.mde.nmc.nway.RefEdgeMulti;
import edu.ustb.sei.mde.nmc.nway.ValEdge;
import edu.ustb.sei.mde.nmc.nway.ValEdgeMulti;

public class TestNWay {

	public static void main(String[] args) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		URI baseURI = URI.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\ecore\\bank.ecore");
		URI branch1URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\ecore\\bank1.ecore");
		URI branch2URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\ecore\\bank2.ecore");
		URI branch3URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\ecore\\bank3.ecore");

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
			resourceMap.put(resource, i-1); // Ϊ�˷����¼�¼�Ԫ�������ĸ���֧ģ��
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
		Map<EObject, List<EObject>> nodeMatchGroupMap = new HashMap<>();
		Map<Integer, List<EObject>> addMap = new HashMap<>();
		// ��Ҫ��¼���¼ӵ�ƥ���飬Ϊ��checkValid
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
					List<EObject> list = nodeMatchGroupMap.get(baseEObject);
					if (list == null) {
						list = new ArrayList<>();
						nodeMatchGroupMap.put(baseEObject, list);
						// ע��baseEObjectû��list�У���ʱ��Ӱ�죩
						nodesIndexMap.put(baseEObject, list);
					}
					list.add(branchEObject);	// ����<eb, {e1, null, e3, ..., en}>

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
		
		
		
		
		// tmp
		System.out.println("\n\n\n----------------------matchGroupMap: ");
		nodeMatchGroupMap.forEach((key, value) -> {
			System.out.println("key: " + key);
			value.forEach(e -> {
				System.out.println(e);
			});
			System.out.println();
		});
		System.out.println("\n\n\n");
		
		
		
		

		// Ԥƥ����Ϣ������֧i����֧j��Ϊ��
		MultiKeyMap<Integer, List<Match>> preMatchMap = new MultiKeyMap<>();
		nodeMatchGroupMap.values().forEach(value -> {
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
		System.out.println("\n\n\n----------------------edges: ");
		edges.forEach(e -> {
			System.out.println(e.getLeft());
			System.out.println(e.getRight());
			System.out.println();
		});
		System.out.println("\n\n\n");

		// ���š�����ѡ��
		List<List<EObject>> nodeMatchGroupList = new ArrayList<>();

		MaximalCliquesWithPivot ff = new MaximalCliquesWithPivot();
		ff.initGraph(vertices, edges);
		List<List<EObject>> maximalCliques = new ArrayList<>();
		if (vertices.size() > 0 && edges.size() > 0) {
			ff.Bron_KerboschPivotExecute(maximalCliques);
		}

		// tmp
		System.out.println("\n\n\n----------------------maximalCliques: ");
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
						sum = 1; // sum��Ϊ0�������ڵ���5����sumΪ0ʱ������Ϊ1��Ϊ��֮��ĳ���
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
			maximalCliques.remove(matchGroup);
			
			List<EObject> nodeMatchGroup = new ArrayList<>(Collections.nCopies(size-1, null));
			matchGroup.forEach(e -> {
				Integer index =  resourceMap.get(e.eResource());
				nodeMatchGroup.set(index, e);	// ����λ��Ϊnull
				nodeMatchGroupList.add(nodeMatchGroup);				
				// ����nodesIndexMap��need to improve?
				nodesIndexMap.put(e, nodeMatchGroup);
			});
							
			// tmp
			System.out.println("**************��ѡ����***************");
			matchGroup.forEach(e -> {
				System.out.println(e);
			});
			System.out.println("\n\n\n\n\n");

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
		System.out.println("\n\n\n----------------------matchGroupList: ");
		nodeMatchGroupList.forEach(list -> {
			list.forEach(e -> {
				System.out.println(e);
			});
			System.out.println();
		});
		System.out.println("\n\n\n");
		
		
		
				
		// PENDING: �ߵ�ID��-> �ߵ�ƥ����
		Map<ValEdge, List<ValEdge>> valEdgeMatchGroupMap = new HashMap<>();
		Map<ValEdgeMulti, List<ValEdgeMulti>> valEdgeMultiMatchGroupMap = new HashMap<>();		
		
		Map<RefEdge, List<RefEdge>> refEdgeMatchGroupMap = new HashMap<>();
		Map<RefEdgeMulti, List<RefEdgeMulti>> refEdgeMultiMatchGroupMap = new HashMap<>();
				
		nodeMatchGroupMap.forEach((baseEObject, list) -> {
			
			System.out.println("\n\n***************************baseEObject***************************");
			System.out.println("baseEObject: " + baseEObject);
			List<EObject> sourceIndex = nodesIndexMap.get(baseEObject);	// e.sourceIndex; 
			
			EClass eClass = baseEObject.eClass();
			
			for(EAttribute a : eClass.getEAllAttributes()) {
				if(a.isMany() == false) {	// ��ֵ����
					Object target = baseEObject.eGet(a);	
					ValEdge valEdge = new ValEdge(a, sourceIndex, target);	// target maybe unset
					List<ValEdge> create = new ArrayList<>(Collections.nCopies(size-1, null));
					valEdgeMatchGroupMap.put(valEdge, create);
					
				} else {	// ��ֵ����
					List<Object> targets = (List<Object>) baseEObject.eGet(a);	// targets maybe unset
					ValEdgeMulti valEdgeMulti = new ValEdgeMulti(a, sourceIndex, targets);
					List<ValEdgeMulti> create = new ArrayList<>(Collections.nCopies(size-1, null));
					valEdgeMultiMatchGroupMap.put(valEdgeMulti, create);
					
				}
			}
			
			System.out.println("-----------------------------------------------------------------------");
			for(EReference r : eClass.getEAllReferences()) {
				if(r.isMany()==false) {	// ��ֵ����
					Object value = baseEObject.eGet(r);	
					List<EObject> targetIndex = nodesIndexMap.get(value);	
					RefEdge refEdge = new RefEdge(r, sourceIndex, targetIndex);	// targetIndex maybe unset
					List<RefEdge> create = new ArrayList<>(Collections.nCopies(size-1, null));
					refEdgeMatchGroupMap.put(refEdge, create);					
					
				} else {	// ��ֵ����
					List<EObject> values = (List<EObject>) baseEObject.eGet(r);
					List<List<EObject>> targetsIndex = new ArrayList<>();
					values.forEach(eObj -> {
						targetsIndex.add(nodesIndexMap.get(eObj));
					});
					
					RefEdgeMulti refEdgeMulti = new RefEdgeMulti(r, sourceIndex, targetsIndex);	// targetsIndex may unset
					List<RefEdgeMulti> create = new ArrayList<>(Collections.nCopies(size-1, null));
					refEdgeMultiMatchGroupMap.put(refEdgeMulti, create);
					
				}
			}
						
			
			for(int i=0; i<size-1; i++) {
				EObject branchEObject = list.get(i);
				System.out.println("\n\n***************************����list***************************");
				if(branchEObject != null) {
					// ��Ϊ��ͬһ��matchGroup��sourceIndex��ͬ
					System.out.println("branchEObject: " + branchEObject);
					
					EClass eClass2 = branchEObject.eClass();	// PENDING: may not the same as eClass.
					
					for(EAttribute a2: eClass2.getEAllAttributes()) {
						if(a2.isMany() == false) {	// ��ֵ����
							Object target2 = branchEObject.eGet(a2);							
							ValEdge valEdge2 = new ValEdge(a2, sourceIndex, target2);
							valEdgeMatchGroupMap.get(valEdge2).set(i, valEdge2);	// PENDING: what if get(valEdge2) is null?
													
						} else {	// ��ֵ����
							List<Object> targets2 = (List<Object>) branchEObject.eGet(a2);						
							ValEdgeMulti valEdgeMulti2 = new ValEdgeMulti(a2, sourceIndex, targets2);
							valEdgeMultiMatchGroupMap.get(valEdgeMulti2).set(i, valEdgeMulti2);
							
						}
					}
					
					System.out.println("-----------------------------------------------------------------------");
					for(EReference r2: eClass2.getEAllReferences()) {
						if(r2.isMany() == false) {	// ��ֵ����
							Object value2 = branchEObject.eGet(r2);
							List<EObject> targetIndex2 = nodesIndexMap.get(value2);	
							
							RefEdge refEdge2 = new RefEdge(r2, sourceIndex, targetIndex2);
							refEdgeMatchGroupMap.get(refEdge2).set(i, refEdge2);
											
						} else {	// ��ֵ����
							List<EObject> values2 = (List<EObject>) branchEObject.eGet(r2);
							
							List<List<EObject>> targetsIndex2 = new ArrayList<>();
							values2.forEach(eObj2 -> {
								targetsIndex2.add(nodesIndexMap.get(eObj2));
							});
							
							RefEdgeMulti refEdgeMulti2 = new RefEdgeMulti(r2, sourceIndex, targetsIndex2);	
							refEdgeMultiMatchGroupMap.get(refEdgeMulti2).set(i, refEdgeMulti2);
														
						}
					}
				} 
			}
		});
		
		// tmp
//		System.out.println("\n\n\n=========================== valEdgeMatchGroupMap ==============================");
//		valEdgeMatchGroupMap.forEach((key, value) -> {
//			System.out.println("key.getType():        " + key.getType());
//			System.out.println("key.getSourceIndex(): " + key.getSourceIndex());
//			System.out.println("key.getTarget():      " + key.getTarget());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():        " + v.getType());
//					System.out.println("v.getSourceIndex(): " + v.getSourceIndex());
//					System.out.println("v.getTarget():      " + v.getTarget());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
//		
//		
//		System.out.println("\n\n\n=========================== valEdgeMultiMatchGroupMap ==============================");
//		valEdgeMultiMatchGroupMap.forEach((key, value) -> {
//			System.out.println("key.getType():         " + key.getType());
//			System.out.println("key.getSourceIndex():  " + key.getSourceIndex());
//			System.out.println("key.getTargets():      " + key.getTargets().size() + " "+ key.getTargets());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():         " + v.getType());
//					System.out.println("v.getSourceIndex():  " + v.getSourceIndex());
//					System.out.println("v.getTargets():      " + v.getTargets().size()+ " " + v.getTargets());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
//
//		
//		
//		System.out.println("\n\n\n=========================== refEdgeMatchGroupMap ==============================");
//		refEdgeMatchGroupMap.forEach((key, value) ->{
//			System.out.println("key.getType():        " + key.getType());
//			System.out.println("key.getSourceIndex(): " + key.getSourceIndex());
//			System.out.println("key.getTargetIndex(): " + key.getTargetIndex());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():        " + v.getType());
//					System.out.println("v.getSourceIndex(): " + v.getSourceIndex());
//					System.out.println("v.getTargetIndex(): " + v.getTargetIndex());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
//		
//		
//		System.out.println("\n\n\n=========================== refEdgeMultiMatchGroupMap ==============================");
//		refEdgeMultiMatchGroupMap.forEach((key, value) ->{
//			System.out.println("key.getType():         " + key.getType());
//			System.out.println("key.getSourceIndex():  " + key.getSourceIndex());
//			System.out.println("key.getTargetsIndex(): " + key.getTargetsIndex().size() + " " + key.getTargetsIndex());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():         " + v.getType());
//					System.out.println("v.getSourceIndex():  " + v.getSourceIndex());
//					System.out.println("v.getTargetsIndex(): " + v.getTargetsIndex().size() + " " + v.getTargetsIndex());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
		
		
		
		// ����¼ӵĵ�
		System.out.println("\n\n\n***********************����¼ӵĵ�**************************");
		Map<ValEdge, List<ValEdge>> valEdgeAddMatchGroupMap = new HashMap<>();
		Map<ValEdgeMulti, List<ValEdgeMulti>> valEdgeMultiAddMatchGroupMap = new HashMap<>();
		
		Map<RefEdge, List<RefEdge>> refEdgeAddMatchGroupMap = new HashMap<>();
		Map<RefEdgeMulti, List<RefEdgeMulti>> refEdgeMultiAddMatchGroupMap = new HashMap<>();
		
		for(List<EObject> list : nodeMatchGroupList) {
			for(int i=0; i<size-1; i++) {
				EObject addEObject = list.get(i);
				if(addEObject != null) {
					System.out.println("addEObject: " + addEObject);
					
					EClass eClass = addEObject.eClass();
					List<EObject> sourceIndex = nodesIndexMap.get(addEObject);
					
					for(EAttribute a : eClass.getEAllAttributes()) {
						if(a.isMany() == false) {	// ��ֵ����
							Object target = addEObject.eGet(a);
							ValEdge valEdge = new ValEdge(a, sourceIndex, target);
							List<ValEdge> exist = valEdgeAddMatchGroupMap.get(valEdge);
							if(exist == null) {
								List<ValEdge> create = new ArrayList<>(Collections.nCopies(size-1, null));
								create.set(i, valEdge);
								valEdgeAddMatchGroupMap.put(valEdge, create);	// ���ǰ��¼�ƥ�����еĵ�һ������base�ĵ�λ����֮��ϲ�����Ҫ����
							}else {
								exist.set(i, valEdge);
							}
							
						} else {	// ��ֵ����
							List<Object> targets = (List<Object>) addEObject.eGet(a);
							ValEdgeMulti valEdgeMulti = new ValEdgeMulti(a, sourceIndex, targets);
							List<ValEdgeMulti> exist = valEdgeMultiAddMatchGroupMap.get(valEdgeMulti);
							if(exist == null) {
								List<ValEdgeMulti> create = new ArrayList<>(Collections.nCopies(size-1, null));
								create.set(i, valEdgeMulti);
								valEdgeMultiAddMatchGroupMap.put(valEdgeMulti, create);
							} else {
								exist.set(i, valEdgeMulti);
							}
						}
					}
					
					for(EReference r : eClass.getEAllReferences()) {
						if(r.isMany() == false) {	// ��ֵ����
							Object value = addEObject.eGet(r);
							List<EObject> targetIndex = nodesIndexMap.get(value);
							RefEdge refEdge = new RefEdge(r, sourceIndex, targetIndex);
							List<RefEdge> exist = refEdgeAddMatchGroupMap.get(refEdge);
							if(exist == null) {
								List<RefEdge> create = new ArrayList<>(Collections.nCopies(size-1, null));
								create.set(i, refEdge);
								refEdgeAddMatchGroupMap.put(refEdge, create);
							} else {
								exist.set(i, refEdge);
							}
							
						} else {	// ��ֵ����
							List<Object> values = (List<Object>) addEObject.eGet(r);
							List<List<EObject>> targetsIndex = new ArrayList<>();
							values.forEach(v -> {
								targetsIndex.add(nodesIndexMap.get(v));
							});
							RefEdgeMulti refEdgeMulti = new RefEdgeMulti(r, sourceIndex, targetsIndex);
							List<RefEdgeMulti> exist = refEdgeMultiAddMatchGroupMap.get(refEdgeMulti);
							if(exist == null) {
								List<RefEdgeMulti> create = new ArrayList<>(Collections.nCopies(size-1, null));
								create.set(i, refEdgeMulti);
								refEdgeMultiAddMatchGroupMap.put(refEdgeMulti, create);
							} else {
								exist.set(i, refEdgeMulti);
							}
						}
					}
				}			
			}
			System.out.println("============================");
		}

		// tmp
//		System.out.println("*******************************valEdgeAddMatchGroupMap********************************");
//		valEdgeAddMatchGroupMap.forEach((key, value) -> {
//			System.out.println("key.getType():        " + key.getType());
//			System.out.println("key.getSourceIndex(): " + key.getSourceIndex());
//			System.out.println("key.getTarget():      " + key.getTarget());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():        " + v.getType());
//					System.out.println("v.getSourceIndex(): " + v.getSourceIndex());
//					System.out.println("v.getTarget():      " + v.getTarget());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
//		
//		
//		System.out.println("*******************************valEdgeAddMatchGroupMap********************************");
//		valEdgeMultiAddMatchGroupMap.forEach((key, value) -> {
//			System.out.println("key.getType():         " + key.getType());
//			System.out.println("key.getSourceIndex():  " + key.getSourceIndex());
//			System.out.println("key.getTargets():      " + key.getTargets().size() + " "+ key.getTargets());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():         " + v.getType());
//					System.out.println("v.getSourceIndex():  " + v.getSourceIndex());
//					System.out.println("v.getTargets():      " + v.getTargets().size()+ " " + v.getTargets());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
//		
//		
//		System.out.println("*******************************refEdgeAddMatchGroupMap********************************");
//		refEdgeAddMatchGroupMap.forEach((key, value) ->{
//			System.out.println("key.getType():        " + key.getType());
//			System.out.println("key.getSourceIndex(): " + key.getSourceIndex());
//			System.out.println("key.getTargetIndex(): " + key.getTargetIndex());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():        " + v.getType());
//					System.out.println("v.getSourceIndex(): " + v.getSourceIndex());
//					System.out.println("v.getTargetIndex(): " + v.getTargetIndex());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
//		
//		
//		System.out.println("*******************************refEdgeMultiAddMatchGroupMap********************************");
//		refEdgeMultiAddMatchGroupMap.forEach((key, value) ->{
//			System.out.println("key.getType():         " + key.getType());
//			System.out.println("key.getSourceIndex():  " + key.getSourceIndex());
//			System.out.println("key.getTargetsIndex(): " + key.getTargetsIndex().size() + " " + key.getTargetsIndex());
//			
//			value.forEach(v -> {
//				if(v != null) {
//					System.out.println("v.getType():         " + v.getType());
//					System.out.println("v.getSourceIndex():  " + v.getSourceIndex());
//					System.out.println("v.getTargetsIndex(): " + v.getTargetsIndex().size() + " " + v.getTargetsIndex());
//				} else {
//					System.out.println(v);
//				}
//			});
//			System.out.println("-----------------------------------------------------");
//		});
//		System.out.println("\n\n\n");
		

		// �ȼ����ĺϲ����
		URI m1URI = URI.createFileURI("E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college_m1.xmi");
		Resource m1Resource = new ResourceImpl(m1URI);
		List<Conflict> conflicts = new ArrayList<>();
		
		nodeMatchGroupMap.forEach((baseEObject, list) -> {
			// �ȼ����ĺϲ�����
			EClass baseEClass = baseEObject.eClass();
			EClass finalEClass = baseEClass;
			// ��¼���ܲ����ĳ�ͻ
			List<Integer> firstMayConflict = new ArrayList<>();
			List<Integer> secondMayConfclit = new ArrayList<>();
			for(int i=0; i<size-1; i++) {
				EObject branchEObject = list.get(i);
				if(branchEObject == null) {
					firstMayConflict.add(i);
				} else {
					EClass branchEClass = branchEObject.eClass();
					if(branchEClass != baseEClass) {
						secondMayConfclit.add(i);
					}
					finalEClass = computeSubType(finalEClass, branchEClass);	
				}
			}
			
			if(finalEClass == null) {
				// ��ͻ�����Ͳ�����
				
				
			} else if(firstMayConflict.size()> 0 && finalEClass!=baseEClass) {
				// ��ͻ��ɾ���ڵ�-�޸Ľڵ�����
				Conflict conflict = new Conflict(firstMayConflict, ConflictKind.Delete, secondMayConfclit, ConflictKind.Update, "ɾ���ڵ�-�޸Ľڵ�����");
				System.out.println("*****" + conflict.toString());
				conflicts.add(conflict);
				
			} else if(firstMayConflict.size() == 0){
				EObject finalEObject = EcoreUtil.create(finalEClass);
				m1Resource.getContents().add(finalEObject);	// may not correct?
				// ����nodexIndexMap
				List<EObject> sourceIndex = nodesIndexMap.get(baseEObject);
				nodesIndexMap.put(finalEObject, sourceIndex);
			}
			// ����Ӧ�þ���ɾ��
		});
		
		EClass finalType = null;
		
		for(List<EObject> list : nodeMatchGroupList) {
			for(int i=0; i<size-1; i++) {
				EObject addEObject = list.get(i);
				if(addEObject != null) {
					EClass addEClass = addEObject.eClass();
					if(finalType == null) {
						finalType = addEClass;
					} else {
						
					}
				}
				
			}
		}		
		
		// tmp
		System.out.println("\n\n\n*****************baseEResource");
		baseResource.getAllContents().forEachRemaining(e -> {
			System.out.println(e);
		});
		
		System.out.println("\n\n\n*****************m1Resource");
		m1Resource.getAllContents().forEachRemaining(e -> {
			System.out.println(e);
		});
		
		System.out.println("line 733");
		
		
	}

	private static EClass computeSubType(EClass leftEClass, EClass rightEClass) {
		if(leftEClass == rightEClass) {
			return leftEClass;
		}
		if(leftEClass.isSuperTypeOf(rightEClass)) {
			return rightEClass;
		}
		if(rightEClass.isSuperTypeOf(leftEClass)) {
			return leftEClass;
		}		
		return null;
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