package org.biojava.nbio.alignment;

import org.biojava.nbio.alignment.NeedlemanWunsch;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @class NeedlemanTest
 * @author Angel Emilio Capote Perez
 * @author Cristo Manuel Perez Rodriguez
 * @author Elena Rijo Garcia
 * @date 16/01/2022
 * @brief Test de Needleman Wunch.
 */
@DisplayName("Test de Needleman Wunch")
public class NeedlemanTest {

    private static final double PRECISION = 0.00000001;

    private ProteinSequence query, target;
    private GapPenalty gaps;
    private SubstitutionMatrix<AminoAcidCompound> blosum62;
    private NeedlemanWunsch<ProteinSequence, AminoAcidCompound> alignment, self;

    @BeforeEach
    public void setup() throws CompoundNotFoundException {
        query = new ProteinSequence("ARND");
        target = new ProteinSequence("RDG");
        gaps = new SimpleGapPenalty(10, 1);
        blosum62 = SubstitutionMatrixHelper.getBlosum62();
        alignment = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(query, target, gaps, blosum62);
        self = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(query, query, gaps, blosum62);
    }

    /**
     * @brief Test para comprobar que la precision sea la acordada
     */
    @Test
    @DisplayName("Test para comprobar que la precision sea la acordada")
    public void testNeedlemanWunsch() {
        NeedlemanWunsch<ProteinSequence, AminoAcidCompound> nw = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>();
        nw.setQuery(query);
        nw.setTarget(target);
        nw.setGapPenalty(gaps);
        nw.setSubstitutionMatrix(blosum62);
        assertEquals(nw.getScore(), alignment.getScore(), PRECISION);
    }

    /**
     * @brief Test para comprobar que el query sea el esperado
     */
    @Test
    @DisplayName("Test para comprobar que el query sea el esperado")
    public void testGetQuery() {
        assertEquals(alignment.getQuery(), query);
        assertEquals(self.getQuery(), query);
    }

    /**
     * @brief Test para comprobar que el target sea el esperado
     */
    @Test
    @DisplayName("Test para comprobar que el target sea el esperado")
    public void testGetTarget() {
        assertEquals(alignment.getTarget(), target);
        assertEquals(self.getTarget(), query);
    }

    /**
     * @brief Test para comprobar que el gap penalty sea el esperado
     */
    @Test
    @DisplayName("Test para comprobar que el gap penalty sea el esperado")
    public void testGetGapPenalty() {
        assertEquals(alignment.getGapPenalty(), gaps);
        assertEquals(self.getGapPenalty(), gaps);
    }

}