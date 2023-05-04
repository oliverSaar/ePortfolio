package org.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowcaseTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private Product mock2;
    Product mock1 = mock(Product.class);

    List mockedList = mock(List.class);


    @Test
    public void listInteractionTest() {

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void stubbingTest() {
        // stubbing before actual execution
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(2)).thenReturn("third");

        // prints "first"
        System.out.println(mockedList.get(0));

        // prints "null" because get(2) was not stubbed
        System.out.println(mockedList.get(2));
    }

    @Test(expected = RuntimeException.class)
    public void verifInOrderTest() {

        // Multiple mocks
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);


        firstMock.add("this mock is called first");
        secondMock.add("this is called second");

        //InOrder object -> gets mocks which need to be verified in order
        InOrder inOrder = inOrder(firstMock, secondMock);

        //making sure the first mock is called before the second one
        inOrder.verify(firstMock).add("this mock is called first");
        inOrder.verify(secondMock).add("this is called second");

        // Heads up: Can also be done with a single Mock that does more than one call


        // verification that a call/interaction never happened on mock
        verify(firstMock, never()).add("have I been added?");


        //If you want to stub a void method, then use doReturn(), doThrow(),... types of methods (else Compiler is unhappy)
        doThrow(new RuntimeException()).when(firstMock).clear();

        firstMock.clear();

    }

}
