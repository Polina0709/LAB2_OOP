import org.example.model.Candy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandyTest {

    private Candy candy;

    @BeforeEach
    void setUp() {
        candy = new Candy();
    }

    @Test
    void testSetAndGetName() {
        candy.setName("Chocolate");
        assertEquals("Chocolate", candy.getName());
    }

    @Test
    void testSetAndGetEnergy() {
        candy.setEnergy(250);
        assertEquals(250, candy.getEnergy());
    }

    @Test
    void testAddType() {
        candy.addType("Chocolate");
        assertEquals(1, candy.getType().size());
        assertEquals("Chocolate", candy.getType().get(0));
    }

    @Test
    void testAddIngredient() {
        candy.addIngredient("Sugar");
        assertEquals(1, candy.getIngredients().size());
        assertEquals("Sugar", candy.getIngredients().get(0));
    }

    @Test
    void testSetAndGetProtein() {
        candy.setProtein(10.5);
        assertEquals(10.5, candy.getProtein());
    }

    @Test
    void testSetAndGetFat() {
        candy.setFat(5.0);
        assertEquals(5.0, candy.getFat());
    }

    @Test
    void testSetAndGetCarbohydrate() {
        candy.setCarbohydrate(30.0);
        assertEquals(30.0, candy.getCarbohydrate());
    }

    @Test
    void testSetAndGetProduction() {
        candy.setProduction("Sweet Factory");
        assertEquals("Sweet Factory", candy.getProduction());
    }

    @Test
    void testToString() {
        candy.setName("Candy");
        candy.setEnergy(200);
        candy.addType("Fruit");
        candy.addIngredient("Fruits");
        candy.setProtein(1.5);
        candy.setFat(0.5);
        candy.setCarbohydrate(50);
        candy.setProduction("Factory");

        String expected = "Candy{" +
                "name='Candy'" +
                ", energy=200" +
                ", types=[Fruit]" +
                ", ingredients=[Fruits]" +
                ", protein=1.5" +
                ", fat=0.5" +
                ", carbohydrate=50.0" +
                ", production='Factory'" +
                '}';

        assertEquals(expected, candy.toString());
    }
}
