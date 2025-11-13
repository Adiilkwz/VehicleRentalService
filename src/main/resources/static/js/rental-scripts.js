document.addEventListener('DOMContentLoaded', function() {
    const bookingForm = document.getElementById('bookingForm');
    if (!bookingForm) return;

    // Fixed costs for client-side estimation (Car, Electric Scooter, Bike)
    const VEHICLE_BASE_COSTS = {
        'Car': 10.00,
        'Bike': 4.00,
        'Electric Scooter': 5.00
    };
    const HOURLY_RATE = 1.0;
    const GPS_FEE = 5.00;
    const INSURANCE_FEE = 8.50;

    const vehicleTypeInput = document.getElementById('vehicleType');
    const durationInput = document.getElementById('durationMinutes');
    const gpsCheckbox = document.getElementById('addGps');
    const insuranceCheckbox = document.getElementById('addInsurance');
    const estimatedCostSpan = document.getElementById('estimatedCost');
    const finalCostHiddenInput = document.getElementById('finalCostHidden');

    function calculateEstimatedCost() {
        const vehicleType = vehicleTypeInput.value;
        const duration = parseInt(durationInput.value) || 0;
        const addGps = gpsCheckbox ? gpsCheckbox.checked : false;
        const addInsurance = insuranceCheckbox ? insuranceCheckbox.checked : false;

        let baseCostPerMinute = VEHICLE_BASE_COSTS[vehicleType] / 60;
        let currentCost = baseCostPerMinute * duration * HOURLY_RATE;

        if (addGps) {
            currentCost += GPS_FEE;
        }
        if (addInsurance) {
            currentCost += INSURANCE_FEE;
        }

        estimatedCostSpan.textContent = currentCost.toFixed(2);
        if (finalCostHiddenInput) {
            finalCostHiddenInput.value = currentCost.toFixed(2);
        }
    }

    // Add event listeners for instant updates
    durationInput.addEventListener('input', calculateEstimatedCost);
    if (gpsCheckbox) gpsCheckbox.addEventListener('change', calculateEstimatedCost);
    if (insuranceCheckbox) insuranceCheckbox.addEventListener('change', calculateEstimatedCost);

    // Initial calculation on page load
    calculateEstimatedCost();
});