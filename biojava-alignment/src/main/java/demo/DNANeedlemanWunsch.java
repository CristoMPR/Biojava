
package demo;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.Alignments.PairwiseSequenceAlignerType;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;

import java.util.HashMap;

/**
 * @class DNANeedlemanWunsch
 * @author Angel Emilio Capote Perez
 * @author Cristo Manuel Perez Rodriguez
 * @author Elena Rijo Garcia
 * @date 16/01/2022
 * @brief Esta clase necesita de un query y un target para que se pueda aplicar el algoritmo Wunsh. Esta clase realiza tales comparaciones de secuencias globales de manera eficiente mediante programación dinámica.
 */
public class DNANeedlemanWunsch {
	private static final HashMap wunschMap = new HashMap();
	private static int no = 1;

	/**
	 * @brief Algortimo para testear la secuencia global por pares
	 * @param query: Primer string para alinear
	 * @param target: Segunda string para alinear
	 */
	public static void DNANeedlemanWunsch(String query, String target) throws Exception {

		GapPenalty penalty = new SimpleGapPenalty(-14, -4);
		PairwiseSequenceAligner<DNASequence, NucleotideCompound> aligner = Alignments.getPairwiseAligner(
				new DNASequence(query, AmbiguityDNACompoundSet.getDNACompoundSet()),
				new DNASequence(target, AmbiguityDNACompoundSet.getDNACompoundSet()),
				PairwiseSequenceAlignerType.GLOBAL,
				penalty, SubstitutionMatrixHelper.getNuc4_4());
		SequencePair<DNASequence, NucleotideCompound>
				alignment = aligner.getPair();

		System.out.println("Alignment: "+ alignment);

		int identical = alignment.getNumIdenticals();
		String[] values = new String[]{alignment.toString(), String.valueOf(identical), String.valueOf(query.length()), String.valueOf(target.length())};
		wunschMap.put(no, values);
		no++;
	}

	/**
	 * @brief Getter del HashMap Wunsch
	 * @return Retorna un HasMap con el valor de la alineacion
	 */
	public static HashMap getWunschMap() {
		return wunschMap;
	}

}
