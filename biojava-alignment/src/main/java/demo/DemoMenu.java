package demo;

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
import java.util.Scanner;

public class DemoMenu {

    static public void main(String[] args) throws Exception {
        DemoMenu demo = new DemoMenu();
        demo.menu();
    }

    private void menu() throws Exception {
        System.out.println("BIOJAVA!");
        System.out.println("¿Que desea hacer?\n   1.GUI DEMO \n   2.Aplicación sin interfaz \n   3.Salir del programa");
        Scanner reader = new Scanner(System.in);
        int eleccion = reader.nextInt();

        switch (eleccion) {
            case 1:
                System.out.println("GUI DEMO ");
                new Frame();
                break;

            case 2:
                valoresMenu();
                break;

            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("¡Introduzca un valor valido!");
                break;
        }
    }

    private void valoresMenu() throws IOException, CompoundNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
