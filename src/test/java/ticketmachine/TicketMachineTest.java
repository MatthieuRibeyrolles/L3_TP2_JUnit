package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        public void noTicketIfLowAmount() {
            machine.insertMoney(PRICE-1);
            assertFalse( machine.printTicket());
        }
        
        @Test
        public void ticketIfGoodAmount() {
            machine.insertMoney(PRICE);
            assertTrue("Le montant est suffisant, voilà votre ticket.", machine.printTicket());
        }
        
        @Test
        public void printTicketLeadsToDecreaseBalance() {
            machine.insertMoney(PRICE);
            machine.printTicket();
            assertEquals(0, machine.getBalance());
        }
        
        @Test
        public void totalUpdateAfterTicket() {
            machine.insertMoney(PRICE);
            assertTrue(machine.getTotal() == 0);
            machine.printTicket();
            assertTrue(machine.getTotal() == PRICE);
        }
        
        @Test
        public void refund() {
            machine.insertMoney(10);
            machine.refund();
            assertTrue("Refund not wworking", machine.getBalance() == 0);
        }
        
        @Test
        public void noNegativeAmountAllowed() {
            machine.insertMoney(-10);
            assertEquals(0, machine.getBalance());
        }
        
        @Test (expected = IllegalArgumentException.class)
        public void noNegativePriceAllowed() {
            new TicketMachine(-10);
        }
}
