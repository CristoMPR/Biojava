package demo;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SequencePair;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Waterman {
    private String uniprotID1;
    private String uniprotID2;
    private ProteinSequence s1;
    private ProteinSequence s2;
    private SequencePair<ProteinSequence, AminoAcidCompound> pair;

    Waterman(String id1, String id2) throws Exception {

        uniprotID1 = id1;
        uniprotID2 = id2;
        s1 = getSequenceForId(uniprotID1);
        s2 = getSequenceForId(uniprotID2);

        SubstitutionMatrix<AminoAcidCompound> matrix = SubstitutionMatrixHelper.getBlosum65();
        GapPenalty penalty = new SimpleGapPenalty();

        int gop = 8;
        int extend = 1;
        penalty.setOpenPenalty(gop);
        penalty.setExtensionPenalty(extend);
        PairwiseSequenceAligner<ProteinSequence, AminoAcidCompound> smithWaterman =
                Alignments.getPairwiseAligner(s1, s2, Alignments.PairwiseSequenceAlignerType.LOCAL, penalty, matrix);

        pair = smithWaterman.getPair();

        toJSON();

    }

    private ProteinSequence getSequenceForId(String uniProtId) throws Exception {
        URL uniprotFasta = new URL(String.format("https://www.uniprot.org/uniprot/%s.fasta", uniProtId));
        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);
        return seq;
    }

    private void toJSON() {
        String json = "{ \n \t\"Proteina\": [\n\t{\n";
        json += "\t\t\"id\": \""+uniprotID1+"\",\n";
        json += "\t\t\"seq\": \""+s1+"\",\n";
        json += "\t\t\"header\": \""+s1.getOriginalHeader()+"\"\n\t},\n";
        json += "\t{\n\t\t\"id\": \""+uniprotID2+"\",\n";
        json += "\t\t\"seq\": \""+s2+"\",\n";
        json += "\t\t\"header\": \""+s2.getOriginalHeader()+"\"\n\t}\n\t],\n";
        json += "\t\"alignment\": \n\""+pair.toString()+"\"\n}";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("alignment.json"))) {
            bw.write(json);
            System.out.println("Fichero creado");
        } catch (IOException ex) {}
    }
}
