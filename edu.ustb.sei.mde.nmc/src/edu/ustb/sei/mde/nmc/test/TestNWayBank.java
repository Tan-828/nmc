package edu.ustb.sei.mde.nmc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import edu.ustb.sei.mde.conflict.ConflictPackage;
import edu.ustb.sei.mde.nmc.compare.IMatchEngine;
import edu.ustb.sei.mde.nmc.compare.match.UseIdentifiers;
import edu.ustb.sei.mde.nmc.compare.start.EMFCompare;
import edu.ustb.sei.mde.nmc.compare.start.MatchEngineFactoryImpl;
import edu.ustb.sei.mde.nmc.compare.start.MatchEngineFactoryRegistryImpl;
import edu.ustb.sei.mde.nmc.nway.NWay;

public class TestNWayBank {
	public static void main(String[] args) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		// 冲突
		ResourceSet resourceSet2 = new ResourceSetImpl();
		resourceSet2.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet2.getPackageRegistry().put(ConflictPackage.eNS_URI, ConflictPackage.eINSTANCE);

		// 指定需要进行排序的类型
		Set<String> needOrderSet = new HashSet<>();
		needOrderSet.add("eClassifiers");
		needOrderSet.add("eParameters");
//		needOrderSet.add("eStructuralFeatures");

		URI m1URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\test\\bank_m1.ecore");
		URI conflictURI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\test\\bank_c.xmi");

		URI baseURI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\test\\bank.ecore");
		URI branch1URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\test\\bank1.ecore");
		URI branch2URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\test\\bank2.ecore");
		URI branch3URI = URI
				.createFileURI("E:\\nmc\\edu.ustb.sei.mde.nmc\\src\\edu\\ustb\\sei\\mde\\nmc\\test\\bank3.ecore");

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
