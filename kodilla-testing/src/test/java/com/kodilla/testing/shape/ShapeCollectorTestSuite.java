package com.kodilla.testing.shape;

//import static org.junit.*; DLACZEGO TO NIE DZIAŁA?
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ShapeCollectorTestSuite {

    private static int testCounter;
    private PrintStream oldOutContent;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void before() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
        oldOutContent = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void after() {
        System.setOut(oldOutContent);
        //outContent.reset();
    }

    @BeforeClass
    public static void beforeAllTests() {
        System.out.println("Starting all tests");
    }

    @AfterClass
    public static void afterAllTests() {
        System.out.println("End of all tests");
    }

    @Test
    public void testAddFirstFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape = new Circle(3);
        //When
        shapeCollector.addFigure(shape);
        //Then
        Shape result = shapeCollector.getFigure(0);
        Assert.assertNotNull(result);
    }

    @Test
    public void testAddSubsequentFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Circle(3);
        Shape shape1 = new Triangle(4.0, 5.0, 6.0);
        shapeCollector.addFigure(shape0);
        //When
        shapeCollector.addFigure(shape1);
        //Then
        Shape result = shapeCollector.getFigure(0);
        Assert.assertNotNull(result);
        result = shapeCollector.getFigure(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void testAddIdenticalFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape = new Circle(3);
        shapeCollector.addFigure(shape);
        //When
        shapeCollector.addFigure(shape);
        //Then
        Shape result = shapeCollector.getFigure(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void testAddNullFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        //When
        try {
            shapeCollector.addFigure(null);
        //Then
            Assert.fail();
        } catch (NullPointerException e) {
            Shape result = shapeCollector.getFigure(0);
            Assert.assertNull(result);
        }
    }

    @Test
    public void testGetTheOnlyFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape = new Square(5);
        shapeCollector.addFigure(shape);
        //When
        Shape result = shapeCollector.getFigure(0);
        //Then
        Assert.assertEquals(result, shape);
    }

    @Test
    public void testGetSubsequentFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Circle(5.0);
        Shape shape1 = new Square(6.0);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        Shape result = shapeCollector.getFigure(1);
        //Then
        Assert.assertEquals(result, shape1);
    }

    @Test
    public void testGetPreviousFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Circle(5.0);
        Shape shape1 = new Square(6.0);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        Shape result = shapeCollector.getFigure(0);
        //Then
        Assert.assertEquals(result, shape0);
    }

    @Test
    public void testGetFigureFromEmptyCollection() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        //When
        Shape result = shapeCollector.getFigure(0);
        //Then
        Assert.assertNull(result);
    }

    @Test
    public void testGetFigureWithNegativeIndex() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Circle(5.0);
        Shape shape1 = new Square(6.0);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        try {
            Shape result = shapeCollector.getFigure(-1);
        //Then
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void testGetFigureWithTooHighIndex() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Circle(5.0);
        Shape shape1 = new Square(6.0);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        Shape result = shapeCollector.getFigure(2);
        //Then
        Assert.assertNull(result);
    }

    @Test
    public void testRemoveTheOnlyFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape = new Triangle(3.0,4.0,5.0);
        shapeCollector.addFigure(shape);
        //When
        boolean result = shapeCollector.removeFigure(shape);
        //Then
        Assert.assertTrue(result);
        Shape resultShape = shapeCollector.getFigure(0);
        Assert.assertNull(resultShape);
    }

    @Test
    public void testRemoveSubsequentFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Triangle(3.0,4.0,5.0);
        Shape shape1 = new Triangle(3.5,4.5, 5.5);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        boolean result = shapeCollector.removeFigure(shape1);
        //Then
        Assert.assertTrue(result);
        Shape resultShape = shapeCollector.getFigure(0);
        Assert.assertEquals(resultShape, shape0);
        resultShape = shapeCollector.getFigure(1);
        Assert.assertNull(resultShape);
    }

    @Test
    public void testRemovePreviousFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Square(1.5);
        Shape shape1 = new Circle(3.14);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        boolean result = shapeCollector.removeFigure(shape0);
        //Then
        Assert.assertTrue(result);
        Shape resultShape = shapeCollector.getFigure(0);
        Assert.assertEquals(resultShape, shape1);
        resultShape = shapeCollector.getFigure(1);
        Assert.assertNull(resultShape);
    }

    @Test
    public void testRemoveNullFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Square(1.5);
        Shape shape1 = new Circle(3.14);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        try {
            boolean result = shapeCollector.removeFigure(null);
        //Then
            Assert.fail();
        } catch (NullPointerException e) {
            Shape resultShape = shapeCollector.getFigure(1);
            Assert.assertNotNull(resultShape); // figure #1 still exists - nothing removed
        }
    }

    @Test
    public void testRemoveNonExistingFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Square(1.5);
        Shape shape1 = new Circle(3.14);
        Shape shapeOther = new Triangle(9.5, 2.8, 9.6);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        //When
        boolean result = shapeCollector.removeFigure(shapeOther);
        //Then
        Assert.assertFalse(result);
        Shape resultShape = shapeCollector.getFigure(1);
        Assert.assertNotNull(resultShape); // figure #1 still exists - nothing removed
    }

    @Test
    public void testRemoveIdenticalFigure() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape = new Square(1.5);
        shapeCollector.addFigure(shape);
        shapeCollector.addFigure(shape);
        //When
        boolean result = shapeCollector.removeFigure(shape);
        //Then
        Assert.assertTrue(result);
        Shape resultShape = shapeCollector.getFigure(1);
        Assert.assertNull(resultShape); // figure #1 does not exist - at least 1 removed
        resultShape = shapeCollector.getFigure(0);
        Assert.assertNotNull(resultShape); // figure #0 still exists - exactly 1 removed
    }

    @Test
    public void testShowFiguresNotEmptyCollection() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        Shape shape0 = new Square(1.5);
        Shape shape1 = new Circle(0.25);
        Shape shape2 = new Triangle(5.0, 4.0, 3.0);
        shapeCollector.addFigure(shape0);
        shapeCollector.addFigure(shape1);
        shapeCollector.addFigure(shape2);
        //When
        shapeCollector.showFigures();
        //Then
        Assert.assertEquals(shape0.toString() + '\n' + shape1.toString() + '\n' + shape2.toString() + '\n',
                outContent.toString());
    }

    @Test
    public void testShowFiguresEmptyCollection() {
        //Given
        ShapeCollector shapeCollector = new ShapeCollector();
        //When
        shapeCollector.showFigures();
        //Then
        Assert.assertEquals("\n", outContent.toString());
    }

}
