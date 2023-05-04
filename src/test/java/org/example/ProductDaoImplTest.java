package org.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class ProductDaoImplTest {

    private ProductDaoImpl testee;
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = mock(EntityManager.class);
        testee = new ProductDaoImpl(entityManager);
    }


    @Test
    public void testInsert() {

        //TODO  Create a testProduct Object and add it with the ProductDaoImpl
        // we want to test if the Entitymanager persists (more or less adds) the correct object to the database
        // (Hint: the ProductDaoImpl has been already created inside the setUp method)

        Product testProduct = new Product("test", 10);
        testee.add(testProduct);

        verify(entityManager).persist(eq(testProduct));
    }


    @Test
    public void testGetCost() {
        //TODO Create a Product mock and stub the getCost() method with 13.37


        Product productMock = mock(Product.class);
        when(productMock.getCost()).thenReturn(13.37);

        assertThat(productMock.getCost(), is(equalTo(13.37)));
    }


    @Test
    public void testAddInOrder() {

        //TODO Create a List mock and two Products then add the products one by one to the List mock
        // you should *verify* if the products are added *in order*

        List productsListMock = mock(List.class);

        Product product = new Product("product", 1);
        Product product2 = new Product("product2", 2);

        productsListMock.add(product);
        productsListMock.add(product2);

        InOrder inOrder = inOrder(productsListMock);

        inOrder.verify(productsListMock).add(product);
        inOrder.verify(productsListMock).add(product2);


    }

    @Test
    public void testAddProduct() {

        //TODO  Write a test to make sure, the addProduct method of productService calls
        // the add method of ProductDao with the correct Product-Object
        //      (Hint: only mock the Dao, since this it is what we want to test)


        ProductDao productDaoMock = mock(ProductDao.class);
        ProductService productService = new ProductService(productDaoMock);


        Product product = new Product("testee", 10);

        productService.addProduct(product);

        verify(productDaoMock).add(product);
    }
}
