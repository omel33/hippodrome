import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class HorseTest {

    @Test
    void constructorShouldThrowExceptionWhenNameIsNull() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Horse(null, 2.0, 3.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionWhenNameIsBlank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("   ", 2.0, 3.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", -1.0, 3.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", 2.0, -3.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    void getNameShouldReturnCorrectValue(){
        Horse horse=new Horse("Test",2.0,3.0);
        assertEquals("Test",horse.getName());
    }
    @Test
    void getSpeedShouldReturnCorrectValue(){
        Horse horse=new Horse("Test",2.0,3.0);
        assertEquals(2.0,horse.getSpeed());
    }
    @Test
    void getDistanceShouldReturnCorrectValue(){
        Horse horse=new Horse("Test",2.0,3.0);
        assertEquals(3.0,horse.getDistance());
    }
    @Test
    void moveShouldCallGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Test", 1);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}

