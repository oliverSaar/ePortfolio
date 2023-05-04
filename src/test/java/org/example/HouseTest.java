package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HouseTest {
    @Mock
    private House houseMock;

    @Test
    public void testGetFloors() {
        when(houseMock.getFloors()).thenReturn("3");

        String testee = houseMock.getFloors();

        verify(houseMock).getFloors();
        assertThat(testee, is(equalTo("3")));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetFloorsException() {
        when(houseMock.getFloors())
                .thenThrow(IllegalArgumentException.class);


        houseMock.getFloors();

        assertThrows(IllegalArgumentException.class, () -> {
        });
    }

    @Test
    public void testSetRooms() {
        houseMock.setRooms("7");
        verify(houseMock).setRooms("7");
    }

}
