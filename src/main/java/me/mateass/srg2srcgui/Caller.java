package me.mateass.srg2srcgui;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.srg2source.ast.RangeExtractor;
import net.minecraftforge.srg2source.rangeapplier.RangeApplier;

public class Caller 
{
	public static void callRangeExtractor(File srcDir, File libDir, File outDir) throws Exception {
		if (new File(outDir + File.separator + "rangemap.rng").isFile()) {
			System.out.println("test");
			return;
		}
		boolean isSuccessfull = RangeExtractor.extract(srcDir, libDir,new File(outDir + File.separator + "rangemap.rng"));
		if (isSuccessfull) {
			return;
		} else {
			throw new Exception("Unsuccessfull range extraction!");
		}
	}
	
	public static void callRangeApplyer(File srcDir, File srgDir, File outDir) throws Exception {
		List<File> srgs = Lists.newArrayList();
		for (File f : srgDir.listFiles()) {
			srgs.add(f);
		}
		RangeApplier.apply(srcDir, new File(outDir + File.separator + "rangemap.rng"), srgs, new File(outDir + File.separator + "output"));
	}
	
	public static void callCodeFixer() throws Exception {
		throw new Exception("Uniplemented Method");
	}
}
