unitofmeasure
=============

Scala library to associate values to unit of measure type and scale. Quantity is intended
to replace passing unitless/scaleless values through interfaces. Many times it is
not obvious which unit/scale an interface requires. Using Quantity allows for
interfaces to allow any unit/scale that the caller desires.

Quantity Types
--------------
* Angle
* Frequency
* Length
* Temperature
* Time

Examples
---------------

Example of creating a Length Quantity and conversion between units and scale.

### Creation

    val kmValue = 12.34
    val kmUnit = METER
    val kmScale = KILO
    val kmQuantity = Quantity(kmValue, kmUnit, kmScale)

### Convert scale to MILLI

    val milliQuantity = kmQuantity.convertScaleTo(MILLI)

### Convert unit to FOOT

    val footQuantity = kmQuantity.convertScaleTo(NONE).convertUnitTo(FOOT)

### Compare two lengths

The quantities are converted to a common unit and scale when compared.

    milliQuantity.compareTo(kmQuantity)

### Add two lengths

The quantities are converted to a common unit and scale when added. The sum will
be returned in the unit/scale of the object instance that add method is called on.

    val sum = milliQuantity.add(kmQuantity)

### Subtract two lengths

The quantities are converted to a common unit and scale when subtracted. The sum will
be returned in the unit/scale of the object instance that subtract method is called on.

    val diff = kmQuantity.sub(milliQuantity)

### Get min and max

The quantities are converted to a common unit and scale when min/max are evaluated.

    val microQuantity = Quantity(456, METER, MICRO)
    val min = microQuantity.min(kmQuantity)
    val max = microQuantity.max(kmQuantity)
