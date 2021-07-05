package com.turstpilot.rabbitchallenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Md5ServiceTest {
    @Autowired
    Md5Service md5Service;
    private String HINT = "poultry outwits ants";
    private String HASHED1 = "e4820b45d2277f3844eac66c903e84be";
    private String HASHED2 = "23170acc097c24edb98fc5488ab033fe";
    private String HASHED3 = "665e5bcb0c20062fe8abaaf4628bb154";
    private String PHRASE1 = "printout stout yawls";
    private String PHRASE2 = "ty outlaws printouts";
    private String PHRASE3 = "wu lisp not statutory";

    private String HINT2 = "trust pilot";
    private String HASHED4 = "ba1fdc3bf6cdcbe7bd7867470ebd3dd2";
    private String PHRASE4 = "trot li pust";

    private String HINT3 = "cannot find it";
    private String HASHED5 = "056ca90e61a018955d4924385114a00c";
    private String PHRASE5 = "";

    @Test
    void decodeMD5Hashed() throws IOException {
        Set<String> anagramwordlist1 = md5Service.decodeMD5Hashed(HINT, HASHED1, "anagramwordlist");
        assertEquals(PHRASE1,anagramwordlist1.iterator().next());
        Set<String> anagramwordlist2 = md5Service.decodeMD5Hashed(HINT, HASHED2, "anagramwordlist");
        assertEquals(PHRASE2,anagramwordlist2.iterator().next());
        Set<String> anagramwordlist3 = md5Service.decodeMD5Hashed(HINT, HASHED3, "anagramwordlist");
        assertEquals(PHRASE3,anagramwordlist3.iterator().next());

        Set<String> anagramwordlist4 = md5Service.decodeMD5Hashed(HINT2, HASHED4, "anagramwordlist");
        assertEquals(PHRASE4,anagramwordlist4.iterator().next());

        Set<String> anagramwordlist5 = md5Service.decodeMD5Hashed(HINT3, HASHED5, "anagramwordlist");
        assertTrue(anagramwordlist5.isEmpty());
    }
}