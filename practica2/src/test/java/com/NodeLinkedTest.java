package com;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.LinkedNode;

public class NodeLinkedTest {
    private LinkedNode<Integer> node;

    @BeforeEach
    public void initTests() {
        node = new LinkedNode<Integer>(1);
    }

    @Test
    public void testNodeLinked() {
        String cadena = node.toString();
        assertTrue(cadena.contains("1"));
    }
    
}
