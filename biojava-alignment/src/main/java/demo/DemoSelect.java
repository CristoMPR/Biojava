package demo;

import Interfaz.Frame;
import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.AmbiguityDNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DemoSelect {

    static public void main(String[] args) throws Exception {
        System.out.println(args[0]);

        if(args[0].equals(Integer.toString(1)) ){
            System.out.println("GUI DEMO ");
            new Frame();
        } else if(args[0].equals(Integer.toString(2))){
            System.out.println("GUI Sin Interfaz ");
            if(args.length == 1)
                valoresTerminal("", "");
            else
                valoresTerminal(args[1], args[2]);
        } else if (args[0].equals(Integer.toString(3))){
            System.exit(0);
        } else {
            System.out.println("¡Introduzca un valor valido!");
        }
    }
    
    private static void valoresTerminal(String id1, String id2) throws IOException, CompoundNotFoundException, Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("Quiere usar: \nwaterman: 1\nWunsch: 2");
        //String option = reader.readLine();


        if (!id1.isEmpty() && !id2.isEmpty()) {
            Waterman wat = new Waterman(id1, id2);
        } else {
            System.out.println("Introduzca los valores del 'Query': ");
            String query = reader.readLine();

            System.out.println("Introduzca los valores del 'Target':");
            String target = reader.readLine();

            GapPenalty penalty = new SimpleGapPenalty(-14, -4);
            PairwiseSequenceAligner<DNASequence, NucleotideCompound> aligner = Alignments.getPairwiseAligner(
                    new DNASequence(query, AmbiguityDNACompoundSet.getDNACompoundSet()),
                    new DNASequence(target, AmbiguityDNACompoundSet.getDNACompoundSet()),
                    Alignments.PairwiseSequenceAlignerType.GLOBAL,
                    penalty, SubstitutionMatrixHelper.getNuc4_4());
            SequencePair<DNASequence, NucleotideCompound>
                    alignment = aligner.getPair();

            System.out.println("Alignment: "+ alignment);

            int identical = alignment.getNumIdenticals();
            System.out.println("Number of identical residues: "+ identical);
            System.out.println("% identical query: "+ identical / (float) query.length());
            System.out.println("% identical target: "+ identical / (float) target.length());
        }





    }
}
