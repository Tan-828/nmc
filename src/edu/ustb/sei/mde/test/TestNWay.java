package edu.ustb.sei.mde.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import edu.ustb.sei.mde.compare.Comparison;
import edu.ustb.sei.mde.compare.IComparisonScope;
import edu.ustb.sei.mde.compare.IMatchEngine;
import edu.ustb.sei.mde.compare.Match;
import edu.ustb.sei.mde.compare.internal.ComparisonSpec;
import edu.ustb.sei.mde.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.compare.start.DefaultComparisonScope;
import edu.ustb.sei.mde.compare.start.EMFCompare;
import edu.ustb.sei.mde.compare.start.MatchEngineFactoryImpl;
import edu.ustb.sei.mde.compare.start.MatchEngineFactoryRegistryImpl;

public class TestNWay {

	public static void main(String[] args) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		
		URI baseURI = URI.createFileURI("D:\\eclipse-dsl-workspace\\nmc-main2\\nmc-main\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\person.ecore");
		URI branch1URI = URI
				.createFileURI("D:\\eclipse-dsl-workspace\\nmc-main2\\nmc-main\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\person1.ecore");
		URI branch2URI = URI
				.createFileURI("D:\\eclipse-dsl-workspace\\nmc-main2\\nmc-main\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\ecore\\person2.ecore");
		
		List<URI> uriList = new ArrayList<>();
		uriList.add(baseURI);
		uriList.add(branch1URI);
		uriList.add(branch2URI);

		List<Resource> resourceList = new ArrayList<>();
		Map<Resource, Integer> resourceMap = new HashMap<>();
		for(int i=0; i<uriList.size(); i++) {
			Resource resource = resourceSet.getResource(uriList.get(i), true);
			resourceList.add(resource);
			resourceMap.put(resource, i);	// 为了方便记录新加元素属于哪个分支模型
		}
		
		// never use identifiers
		IMatchEngine.Factory.Registry registry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
		final MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl(UseIdentifiers.NEVER);
		matchEngineFactory.setRanking(20);
		registry.add(matchEngineFactory);
		
		// 只使用一个EMFCompare
		EMFCompare build = EMFCompare.builder().setMatchEngineFactoryRegistry(registry).build();
		
		Comparison comparison = null;
		IComparisonScope scope = null;
		Resource baseResource = resourceList.get(0);
		Resource branchResource = null;
		
		// 将原始模型与各个分支模型进行二向匹配
		// 匹配上同一个原始元素的，划分到相同的匹配组
		// 还要记录新加的元素
		// 还要记录预匹配信息
		Map<EObject, List<EObject>> matchGroupMap = new HashMap<>();
		Map<Integer, List<EObject>> addMap = new HashMap<>();
		Map<Integer, List<Match>> preMatchMap = new HashMap<>();
		
		for(int i=1; i<resourceList.size(); i++) {
			branchResource = resourceList.get(i);
			scope = new DefaultComparisonScope(baseResource, branchResource, null);
			long start = System.currentTimeMillis();
			comparison = build.compare(scope);
			long end = System.currentTimeMillis();
			System.out.println("MATCH TIME: " + (end-start) + " ms.\n");
			
			for(Match match : comparison.getMatches()) {				
				EObject baseEObject = match.getLeft();
				EObject branchEObject = match.getRight();
				if(baseEObject != null) {			
					List<EObject> list = matchGroupMap.get(baseEObject);
					if(list == null) {
						list = new ArrayList<>();
						matchGroupMap.put(baseEObject, list);
					}								
					list.add(branchEObject);		
					
					// 预匹配信息
					List<Match> matches = preMatchMap.get(i);
					if(matches == null) {
						matches = new ArrayList<>();
						preMatchMap.put(i, matches);
					}
					matches.add(match);
					
				} else {	
					List<EObject> addList = addMap.get(i);	
					if(addList == null) {
						addList = new ArrayList<>();
						addMap.put(i, addList);		// 将相同分支模型的所有新加元素放到一起
					}
					addList.add(branchEObject);					
				}						
			}
			
		}
					
		// tmp
		matchGroupMap.forEach((key, value) -> {
			System.out.println("key: " + key);
			value.forEach(v -> {
				System.out.println("v: " + v);
			});
			System.out.println("----------------------------------------");
		});
		
		// tmp
		addMap.forEach((key, value) -> {
			System.out.println("i: " + key);
			value.forEach(v -> {
				System.out.println("add: " + v);
			});
			System.out.println("*****************************************");
		});
		
		// 对于新加元素，两两比较分支模型
		for(int i=1; i<resourceList.size()-1; i++) {
			List<EObject> leftEObjects = addMap.get(i);
			if(leftEObjects != null) {
				
				List<Match> preMatchesI = preMatchMap.get(i);
				
				for(int j=i+1; j<resourceList.size(); j++) {
					List<EObject> rightEObjects = addMap.get(j);
					if(rightEObjects != null) {
						
						List<Match> preMatchesJ = preMatchMap.get(j);
						
						Comparison comparisonADD = new ComparisonSpec();
						comparisonADD.getMatches().addAll(preMatchesI);
						comparisonADD.getMatches().addAll(preMatchesJ);
						
						build.compareADD(comparisonADD, leftEObjects, rightEObjects);
						
						// 过滤出新加元素的匹配
						// tmp
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						comparisonADD.getMatches().forEach(m -> {
							System.out.println(m.getLeft());
							System.out.println(m.getRight());
							System.out.println(m.getOrigin());
						});
						System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					}
				}
			}
		}
		
		
	}
}
