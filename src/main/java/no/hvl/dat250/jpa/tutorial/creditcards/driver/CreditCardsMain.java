package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
            PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {


    Pincode pincode = new Pincode();
    pincode.setCode("123");
    pincode.setCount(1);

    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);

    Bank bank = new Bank();
    bank.setName("Pengebank");

    CreditCard creditCard1 = new CreditCard();
    creditCard1.setNumber(123);
    creditCard1.setBalance(1);
    creditCard1.setCreditLimit(2000);
    creditCard1.setOwningbank(bank);
    creditCard1.setPincode(pincode);

    CreditCard creditCard2 = new CreditCard();
    creditCard2.setNumber(12345);
    creditCard2.setBalance(-5000);
    creditCard2.setCreditLimit(-10000);
    creditCard2.setOwningbank(bank);
    creditCard2.setPincode(pincode);

    Customer customer = new Customer();
    customer.setName("Max Mustermann");
    List<CreditCard> creditCardList = new ArrayList<CreditCard>();
    creditCardList.add(creditCard1);
    creditCardList.add(creditCard2);
    customer.setCreditcards(creditCardList);
    List<Address> addressList = new ArrayList<Address>();
    addressList.add(address);
    customer.setAddresses(addressList);

    Set<Customer> customersList = new HashSet<>();
    customersList.add(customer);
    address.setCustomers(customersList);

    Set<CreditCard> ccSet = new HashSet<>();
    ccSet.add(creditCard1);
    ccSet.add(creditCard2);
    bank.setCreditcards(ccSet);

    em.persist(pincode);
    em.persist(address);
    em.persist(bank);
    em.persist(creditCard1);
    em.persist(creditCard2);
    em.persist(customer);

  }
}
