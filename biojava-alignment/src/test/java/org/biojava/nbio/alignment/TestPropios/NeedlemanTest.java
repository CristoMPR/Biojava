package org.biojava.nbio.alignment.TestPropios;

import org.biojava.nbio.alignment.NeedlemanWunsch;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.alignment.template.GapPenalty;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DisplayName("Test de Needleman Wunch")
public class NeedlemanTest {

    private static final double PRECISION = 0.00000001;

    private ProteinSequence query, target;
    private GapPenalty gaps;
    private SubstitutionMatrix<AminoAcidCompound> blosum62;
    private NeedlemanWunsch<ProteinSequence, AminoAcidCompound> alignment, self;

    @Before
    public void setup() throws CompoundNotFoundException {
        query = new ProteinSequence("ARND");
        target = new ProteinSequence("RDG");
        gaps = new SimpleGapPenalty(10, 1);
        blosum62 = SubstitutionMatrixHelper.getBlosum62();
        alignment = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(query, target, gaps, blosum62);
        self = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(query, query, gaps, blosum62);
    }

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

    @Test
    @DisplayName("Test para comprobar que el query sea el esperado")
    public void testGetQuery() {
        assertEquals(alignment.getQuery(), query);
        assertEquals(self.getQuery(), query);
    }

    @Test
    @DisplayName("Test para comprobar que el target sea el esperado")
    public void testGetTarget() {
        assertEquals(alignment.getTarget(), target);
        assertEquals(self.getTarget(), query);
    }

    @Test
    @DisplayName("Test para comprobar que el gap penalty sea el esperado")
    public void testGetGapPenalty() {
        assertEquals(alignment.getGapPenalty(), gaps);
        assertEquals(self.getGapPenalty(), gaps);
    }

}