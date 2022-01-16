package org.biojava.nbio.alignment;

import demo.Waterman;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @class WatermanTest
 * @author Angel Emilio Capote Perez
 * @author Cristo Manuel Perez Rodriguez
 * @author Elena Rijo Garcia
 * @date 16/01/2022
 * @brief Test de Waterman
 */
@DisplayName("Test de Waterman")
public class WatermanTest {

    private Waterman waterman;
    private String uniprotID1;
    private String uniprotID2;
    private String seq1;
    private String seq2;

    @Before
    public void setup() throws Exception {
        uniprotID1 = "P69905";
        uniprotID2 = "P68871";
        waterman = new Waterman(uniprotID1, uniprotID2);
        seq1 = "MVLSPADKTNVKAAWGKVGAHAGEYGAEALERMFLSFPTTKTYFPHFDLSHGSAQVKGHGKKVADALTNAVAHVDDMPNALSALSDLHAHKLRVDPVNFKLLSHCLLVTLAAHLPAEFTPAVHASLDKFLASVSTVLTSKYR";
        seq2 = "MVHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMGNPKVKAHGKKVLGAFSDGLAHLDNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVANALAHKYH";
    }

    /**
     * @brief Test para comprobar el valor de la secuencia
     */
    @Test
    @DisplayName("Test para comprobar el valor de la secuencia")
    public void testGetSequenceForId() throws Exception {
        assertNotNull(waterman.getSequenceForId(uniprotID1));
        assertNotNull(waterman.getSequenceForId(uniprotID2));

        assertEquals(waterman.getSequenceForId(uniprotID1).toString(), seq1);
        assertEquals(waterman.getSequenceForId(uniprotID2).toString(), seq2);
    }

    /**
     * @brief Test para comprobar el valor del id de la proteina
     */
    @Test
    @DisplayName("Test para comprobar el valor del id de la proteina")
    public void testGetters() {
        assertNotNull(waterman.getUniprotID1());
        assertNotNull(waterman.getUniprotID2());

        assertEquals(waterman.getUniprotID1(), uniprotID1);
        assertEquals(waterman.getUniprotID2(), uniprotID2);
    }
}