import static org.junit.jupiter.api.Assertions.*;
import com.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    private BankAccount cuenta;

    @BeforeEach
    public void initTests() {
        cuenta = new BankAccount(100);
    }

    @Test
    public void testWithdraw() {
        assertTrue(cuenta.withdraw(50));
        assertEquals(50, cuenta.getBalance());
    }

    @Test
    public void testDeposit() {
        assertEquals(150, cuenta.deposit(50));
    }

    @Test
    public void testPayment() {
        assertEquals(106.55217268605651, cuenta.payment(1000, 0.04, 12));
    }

    @Test
    public void testPending() {
            assertEquals(10000, cuenta.pending(10000, 0.001, 12, 0));
            assertEquals(8341.651388934994, cuenta.pending(10000, 0.001, 12, 2));
    }

    @Test
    public void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> cuenta.deposit(-50));
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        assertFalse(cuenta.withdraw(150));
    }

    @Test
    public void withdrawAll() {
        assertTrue(cuenta.withdraw(100));
        assertEquals(0, cuenta.getBalance());
    }

    @Test
    public void testDepositZero() {
        assertEquals(100, cuenta.deposit(0));
    }


}
