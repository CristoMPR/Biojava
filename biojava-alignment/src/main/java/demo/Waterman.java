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

import java.io.*;
import java.net.URL;

/**
 * @class Waterman
 * @author Angel Emilio Capote Perez
 * @author Cristo Manuel Perez Rodriguez
 * @author Elena Rijo Garcia
 * @date 16/01/2022
 * @brief Esta clase representa y compara dos secuencias de proteinas, que estan representadas por su ID. Esta clase
 * realiza tales comparaciones de secuencias locales de manera eficiente mediante programación dinámica.
 */
public class Waterman {
    private String uniprotID1;
    private String uniprotID2;
    private ProteinSequence s1;
    private ProteinSequence s2;
    private SequencePair<ProteinSequence, AminoAcidCompound> pair;

    /**
     * @brief Constructor de la clase que llama a los metodos para la comparacion de proteinas
     * @param id1: String identificador de la proteina
     * @param id2: String identificador de la proteina
     */
    public Waterman(String id1, String id2) throws Exception {

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

    /**
     * @brief Getter de la primera proteina
     * @return
     */
    public String getUniprotID1() {
        return uniprotID1;
    }

    /**
     * @brief Getter de la segunda proteina
     * @return
     */
    public String getUniprotID2() {
        return uniprotID2;
    }

    /**
     * @brief Getter de la secuencia de la proteina
     * @param uniProtId: String del ID de la proteina
     * @return La secuencia de la proteina
     */
    public ProteinSequence getSequenceForId(String uniProtId) throws Exception {
        URL uniprotFasta = new URL(String.format("https://www.uniprot.org/uniprot/%s.fasta", uniProtId));
        ProteinSequence seq = FastaReaderHelper.readFastaProteinSequence(uniprotFasta.openStream()).get(uniProtId);
        return seq;
    }

    /**
     * @brief Método para escribir el contenido en un fichero JSON
     */
    private void toJSON() throws IOException {
        String json = "{ \n \t\"Proteina\": [\n\t{\n";
        json += "\t\t\"id\": \""+uniprotID1+"\",\n";
        json += "\t\t\"seq\": \""+s1+"\",\n";
        json += "\t\t\"header\": \""+s1.getOriginalHeader()+"\"\n\t},\n";
        json += "\t{\n\t\t\"id\": \""+uniprotID2+"\",\n";
        json += "\t\t\"seq\": \""+s2+"\",\n";
        json += "\t\t\"header\": \""+s2.getOriginalHeader()+"\"\n\t}\n\t],\n";
        json += "\t\"alignment\": \n\""+pair.toString()+"\"\n}";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Introduzca el nombre del fichero: ");
        String filename = reader.readLine();
        filename += ".json";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(json);
            System.out.println("Fichero creado");
        } catch (IOException ex) {}
    }
}