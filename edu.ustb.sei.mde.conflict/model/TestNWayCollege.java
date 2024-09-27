package edu.ustb.sei.mde.college.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import edu.ustb.sei.mde.college.CollegePackage;
import edu.ustb.sei.mde.conflict.ConflictPackage;
import edu.ustb.sei.mde.nmc.compare.IMatchEngine;
import edu.ustb.sei.mde.nmc.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.nmc.compare.start.EMFCompare;
import edu.ustb.sei.mde.nmc.compare.start.MatchEngineFactoryImpl;
import edu.ustb.sei.mde.nmc.compare.start.MatchEngineFactoryRegistryImpl;
import edu.ustb.sei.mde.nmc.nway.NWay;

public class TestNWayCollege {
	public static void main(String[] args) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(CollegePackage.eNS_URI, CollegePackage.eINSTANCE);

		// 冲突
		ResourceSet resourceSet2 = new ResourceSetImpl();
		resourceSet2.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet2.getPackageRegistry().put(ConflictPackage.eNS_URI, ConflictPackage.eINSTANCE);

		// 指定需要进行排序的类型
		Set<String> needOrderSet = new HashSet<>();
		needOrderSet.add("persons");
		needOrderSet.add("colleges");
		needOrderSet.add("friends");
		needOrderSet.add("title");

		URI m1URI = URI.createFileURI(
				"E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college_m1.xmi");
		URI conflictURI = URI.createFileURI(
				"E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college_c.xmi");

		URI baseURI = URI.createFileURI(
				"E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college.xmi");
		URI branch1URI = URI.createFileURI(
				"E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college1.xmi");
		URI branch2URI = URI.createFileURI(
				"E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college2.xmi");
		URI branch3URI = URI.createFileURI(
				"E:\\eclipse-dsl202012\\edu.ustb.sei.mde.college\\src\\edu\\ustb\\sei\\mde\\college\\xmi\\college3.xmi");

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
			resourceMap.put(resource, i - 1); // 为了方便记录新加元素属于哪个分支模型
		}

		Resource m1Resource = resourceSet.createResource(m1URI); // 用createResource
		Resource conflictResource = resourceSet2.createResource(conflictURI);	

		// never use identifiers
		IMatchEngine.Factory.Registry registry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
		final MatchEngineFactoryImpl matchEngineFactory = new MatchEngineFactoryImpl(UseIdentifiers.NEVER);
		matchEngineFactory.setRanking(20);
		registry.add(matchEngineFactory);

		// 只使用一个EMFCompare
		EMFCompare build = EMFCompare.builder().setMatchEngineFactoryRegistry(registry).build();

		NWay.nWay(resourceList, resourceMap, build, needOrderSet, m1Resource, conflictResource);

	}
}
