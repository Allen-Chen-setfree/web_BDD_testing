package com.automationpractice.stepdefs;

import com.automationpractice.Helper.TestContext;
import com.automationpractice.PageObject.SummerDresses;
import com.automationpractice.Utilities.Entry;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class SummerDressessSteps {

    TestContext testContext;
    SummerDresses summerDressesPage;
    Entry entry;

    public SummerDressessSteps(TestContext context){
        testContext=context;
        entry=context.getEntry();
    }

    @Given("^I am on the Summer Dresses page: \"([^\"]*)\"$")
    public void i_am_on_the_page(String page) throws Exception {
        String url = entry.readProperty().get(page);
        summerDressesPage = new SummerDresses(
                entry.init().navToURL(url)
        );
    }
    @When("^I add \"([^\"]*)\" into cart$")
    public void i_add(String itemName) throws Throwable {
        summerDressesPage.moveToItem(itemName).addToCart();
    }

    @Then("^I should see the info window \"([^\"]*)\"$")
    public void i_should_see_the_info_window(String msg) throws Throwable {
        String actMsg = summerDressesPage.fectchMsgAfterAddItem();
        Assert.assertEquals(msg,actMsg);
    }

    @Then("^\"([^\"]*)\" such item is added to card$")
    public void one_such_item_is_added_to_card(int quantity) throws Throwable {
        int actQuantity = summerDressesPage.getNoAfterAddItem();
        Assert.assertEquals(quantity,actQuantity);
        entry.closeTab();
    }


}
