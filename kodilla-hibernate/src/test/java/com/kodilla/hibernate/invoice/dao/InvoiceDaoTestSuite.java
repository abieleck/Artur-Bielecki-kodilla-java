package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceDaoTestSuite {
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ProductDao productDao;

    @Test
    public void testInvoiceDaoSave() {
        //Given
        Invoice invoice = new Invoice("invoice1");
        Product product1 = new Product("product1");
        Product product2 = new Product("product2");
        Item item1 = new Item(product1, new BigDecimal("1"), 10, invoice);
        Item item2 = new Item(product2, new BigDecimal("2.20"), 20, invoice);
        Item item3 = new Item(product1, new BigDecimal("3.30"), 30, invoice);
        List<Item> items = invoice.getItems();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        //When
        invoiceDao.save(invoice);
        int invoiceId = invoice.getId();
        int item1Id = item1.getId();
        int item2Id = item2.getId();
        int item3Id = item3.getId();
        int product1Id = product1.getId();
        int product2Id = product2.getId();

        //Then
        Assert.assertNotEquals(0, invoiceId);
        Assert.assertNotEquals(0, product1Id);
        Assert.assertNotEquals(0, product2Id);
        Assert.assertNotEquals(0, item1Id);
        Assert.assertNotEquals(0, item2Id);
        Assert.assertNotEquals(0, item3Id);

        //Clean up
        invoiceDao.delete(invoiceId);
        productDao.delete(product1Id);
        productDao.delete(product2Id);

    }
}
