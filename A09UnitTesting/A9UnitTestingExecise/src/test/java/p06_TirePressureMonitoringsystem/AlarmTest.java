package p06_TirePressureMonitoringsystem;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;
import p06_TirePressureMonitoringSystem.Sensor;

public class AlarmTest {

    @Test
    public void testAlarmWithNormalPSIValue() {
        Sensor fakeSensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(fakeSensor);

        Mockito.when(fakeSensor.popNextPressurePsiValue()).thenReturn(20.0);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmWithLowerPSIValue() {
        Sensor fakeSensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(fakeSensor);

        Mockito.when(fakeSensor.popNextPressurePsiValue()).thenReturn(10.0);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmWithHigherPSIValue() {
        Sensor fakeSensor = Mockito.mock(Sensor.class);
        Alarm alarm = new Alarm(fakeSensor);

        Mockito.when(fakeSensor.popNextPressurePsiValue()).thenReturn(25.7);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }




}
