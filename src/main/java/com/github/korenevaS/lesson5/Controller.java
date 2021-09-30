package com.github.korenevaS.lesson5;

import com.github.korenevaS.lesson5.exception.NoSuchBookInBasketException;
import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;
import com.github.korenevaS.lesson5.model.*;
import com.github.korenevaS.lesson5.model.enums.Commands;
import com.github.korenevaS.lesson5.model.enums.MessageType;
import com.github.korenevaS.lesson5.service.BasketService;
import com.github.korenevaS.lesson5.service.CatalogService;
import com.github.korenevaS.lesson5.service.OrderService;
import com.github.korenevaS.lesson5.util.ConsoleUtil;
import com.github.korenevaS.lesson5.util.ValidationUtil;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    private final BasketService basketService = new BasketService();
    private final OrderService orderService = new OrderService();

    public void performOrder() {
        Scanner scan = new Scanner(System.in);
        ConsoleUtil.printMessage(MessageType.ENTER_NAME);
        String nameCustomer = "";
        while (Objects.equals(nameCustomer, "")) {
            String checkName = scan.nextLine();
            if (!ValidationUtil.isNameCorrect(checkName)) {
                ConsoleUtil.printMessage(MessageType.WRONG_NAME);
            } else {
                nameCustomer = checkName;
            }
        }
        ConsoleUtil.printMessage(MessageType.ENTER_PHONE);
        String phoneCustomer = "";
        while (Objects.equals(phoneCustomer, "")) {
            String checkPhone = scan.nextLine();
            if (!ValidationUtil.isPhoneCorrect(checkPhone)) {
                ConsoleUtil.printMessage(MessageType.WRONG_PHONE);
            } else {
                phoneCustomer = checkPhone;
            }
        }

        ConsoleUtil.printMessage(MessageType.ENTER_ADDRESS);
        String addressCustomer = scan.nextLine();

        Customer customer = new Customer(nameCustomer, phoneCustomer, addressCustomer);
        Basket basket = new Basket();

        ConsoleUtil.printWelcomeMessage(customer.getName());
        ConsoleUtil.printHint();

        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (Objects.equals(input, Commands.CATALOG.getCode())) {
                Map<Integer, Book> books = CatalogService.getListBooks();
                books.forEach((index, book) -> System.out.println(index + ": " + book));
            } else if (Objects.equals(input, Commands.BASKET.getCode())) {
                System.out.println(basketService.getBasketDescription(basket));
            } else if (Objects.equals(input, Commands.ORDER.getCode())) {
                Order order = new Order(customer, basket);
                System.out.println(orderService.getOrderDescription(order, basketService.getBasketDescription(basket)));
                if (!basket.isBasketEmpty()) {
                    ConsoleUtil.printConfirmOrder(Commands.CONFIRM.getCode());
                    input = scan.nextLine();
                    if (Objects.equals(input, Commands.CONFIRM.getCode())) {
                        ConsoleUtil.printMessage(MessageType.ORDER_SHIPPED);
                        break;
                    } else {
                        ConsoleUtil.printMessage(MessageType.ORDER_FAILED);
                    }
                }
            } else {
                String[] separator = input.split(" ");
                if (Objects.equals(separator[0], Commands.ADD.getCode()) && separator.length == 2) {
                    try {
                        int bookId = Integer.parseInt(separator[1]);
                        basketService.addPurchaseToBasket(basket, bookId);
                        ConsoleUtil.printMessage(MessageType.ADD_SUCCESS);
                    } catch (NumberFormatException e) {
                        ConsoleUtil.printMessage(MessageType.WRONG_COMMAND);
                        ConsoleUtil.printHint();
                    } catch (NoSuchBookExistsException noSuchBookException2) {
                        System.out.println(MessageType.WRONG_ID.getMessage());
                    }
                } else if (Objects.equals(separator[0], Commands.REMOVE.getCode()) && separator.length == 2) {
                    try {
                        int bookId = Integer.parseInt(separator[1]);
                        basketService.removePurchaseFromBasket(basket, bookId);
                        ConsoleUtil.printMessage(MessageType.REMOVE_SUCCESS);
                    } catch (NumberFormatException e) {
                        ConsoleUtil.printMessage(MessageType.WRONG_COMMAND);
                        ConsoleUtil.printHint();
                    } catch (NoSuchBookInBasketException e) {
                        ConsoleUtil.printRemoveError(e.getBook().getName());
                    } catch (NoSuchBookExistsException noSuchBookException2) {
                    System.out.println(MessageType.WRONG_ID.getMessage());
                }
                } else {
                    ConsoleUtil.printMessage(MessageType.WRONG_COMMAND);
                    ConsoleUtil.printHint();
                }
            }
        }
    }
}
