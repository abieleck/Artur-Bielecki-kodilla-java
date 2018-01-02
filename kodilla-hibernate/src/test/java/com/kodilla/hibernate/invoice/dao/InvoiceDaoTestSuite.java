package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceDaoTestSuite {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ProductDao productDao;

    private static Invoice invoice;
    private static int invoiceId;
    private static int product1Id;
    private static int product2Id;

    @Before
    public void initializeData() {
        invoice = new Invoice("invoice1");
        Product product1 = new Product("product1");
        Product product2 = new Product("product2");
        Item item1 = new Item(product1, new BigDecimal("1"), 10, invoice);
        Item item2 = new Item(product2, new BigDecimal("2.20"), 20, invoice);
        Item item3 = new Item(product1, new BigDecimal("3.30"), 30, invoice);
        List<Item> items = invoice.getItems();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        productDao.save(product1);
        productDao.save(product2);
        invoiceDao.save(invoice);

        invoiceId = invoice.getId();
        product1Id = product1.getId();
        product2Id = product2.getId();
    }

    @After
    public void cleanUp() {
        invoiceDao.delete(invoiceId);
        productDao.delete(product1Id);
        productDao.delete(product2Id);
    }

    @Test
    public void testInvoiceDaoSave() {
        //Given
        //When
        int item1Id = invoice.getItems().get(0).getId();
        int item2Id = invoice.getItems().get(1).getId();
        int item3Id = invoice.getItems().get(2).getId();

        //Then
        Assert.assertNotEquals(0, invoiceId);
        Assert.assertNotEquals(0, product1Id);
        Assert.assertNotEquals(0, product2Id);
        Assert.assertNotEquals(0, item1Id);
        Assert.assertNotEquals(0, item2Id);
        Assert.assertNotEquals(0, item3Id);

    }
}
