package com.kodilla.sudoku.menu;

import com.kodilla.sudoku.Input;
import org.junit.Assert;
import org.junit.Test;

public class MenuTestSuite {

    @Test
    public void testMenu() {
        //Given
        Input.open();
        Menu menu = new Menu("Test menu description", "Test menu prompt:");
        MenuItem menuItem = new MenuItem("1-9", "^[1-9]$", "Enter number 1-9",
                s -> {System.out.println(s);
                      return AppStatus.NORMAL;
                    });
        menu.addItem(menuItem);
        //When
        AppStatus appStatus = menu.execute();
        //Then
        Assert.assertEquals(AppStatus.NORMAL, appStatus);
        //Clean up
        Input.close();
    }
}
