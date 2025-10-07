package com.velicans.steps.store;

import com.velicans.models.Order;
import com.velicans.steps.serenity.StoreSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StoreOperationsSteps {

    @Steps
    StoreSteps storeSteps;

    private Order testOrder;
    private Long orderId;

    @Given("the pet store API is available")
    public void thePetStoreAPISIsAvailable() {
        // Base uri set
    }

    @When("I get the inventory")
    public void iGetTheInventory() {
        storeSteps.getInventory();
    }

    @Then("I should receive the inventory map")
    public void iShouldReceiveTheInventoryMap() {
        // Assertion in step
    }

    @Given("a valid order with petId {int} and quantity {int}")
    public void aValidOrderWithPetIdAndQuantity(int petId, int quantity) {
        testOrder = new Order();
        testOrder.setPetId((long) petId);
        testOrder.setQuantity(quantity);
        testOrder.setStatus("placed");
    }

    @When("I place the order")
    public void iPlaceTheOrder() {
        storeSteps.placeOrder(testOrder);
    }

    @Then("the order should be placed")
    public void theOrderShouldBePlaced() {
        // Assertion in step
    }

    @Given("an order with invalid data")
    public void anOrderWithInvalidData() {
        testOrder = new Order();
        testOrder.setPetId(0L);
        testOrder.setQuantity(-1);
    }

    @When("I try to place the order with invalid input")
    public void iTryToPlaceTheOrderWithInvalidInput() {
        storeSteps.placeOrderInvalidInput(testOrder);
    }

    @Then("I should receive a 400 bad request error")
    public void iShouldReceiveA400BadRequestError() {
        // Assertion in step
    }

    @Given("an existing order with id {int}")
    public void anExistingOrderWithId(int id) {
        testOrder = new Order();
        testOrder.setId((long) id);
        testOrder.setPetId(1L);
        testOrder.setQuantity(1);
        testOrder.setStatus("placed");
        storeSteps.placeOrder(testOrder);
    }

    @When("I get the order by id {int}")
    public void iGetTheOrderById(int id) {
        storeSteps.getOrderById((long) id);
    }

    @Then("I should receive the order details")
    public void iShouldReceiveTheOrderDetails() {
        // Assertion in step
    }

    @Given("an invalid order id like {string}")
    public void anInvalidOrderIdLike(String invalidId) {
        orderId = Long.valueOf(invalidId);
    }

    @When("I try to get the order with invalid id")
    public void iTryToGetTheOrderWithInvalidId() {
        storeSteps.getOrderWithInvalidId("abc");
    }

    @Then("I should receive a 400 error")
    public void iShouldReceiveA400Error() {
        // Assertion in step
    }

    @Given("an order id {long} that does not exist")
    public void anOrderIdThatDoesNotExist(long nonExistentId) {
        orderId = nonExistentId;
    }

    @When("I try to get the order not found")
    public void iTryToGetTheOrderNotFound() {
        storeSteps.getOrderWithNotFoundId(orderId);
    }

    @Then("I should receive a 404 error")
    public void iShouldReceiveA404Error() {
        // Assertion in step
    }

    @Given("an existing order with id {int}")
    public void anExistingOrderWithId1(int id) {
        anExistingOrderWithId(id);
    }

    @When("I delete the order by id {int}")
    public void iDeleteTheOrderById(int id) {
        storeSteps.deleteOrder((long) id);
    }

    @Then("the order should be deleted successfully")
    public void theOrderShouldBeDeletedSuccessfully() {
        // Assertion in step
    }

    @Given("an invalid order id like {string}")
    public void anInvalidOrderIdLike1(String invalidId) {
        orderId = Long.valueOf(invalidId);
    }

    @When("I try to delete the order with invalid id")
    public void iTryToDeleteTheOrderWithInvalidId() {
        storeSteps.deleteOrderWithInvalidId("xyz");
    }

    // Similar for others
    @When("I try to delete the order not found")
    public void iTryToDeleteTheOrderNotFound() {
        storeSteps.deleteOrderNotFound(orderId);
    }
}
