document.addEventListener('DOMContentLoaded', function() {
    const bookingForm = document.getElementById('bookingForm');
    if (!bookingForm) return;

    const VEHICLE_BASE_COSTS = {
        'Car': 45.00,
        'Bike': 8.00,
        'Electric Scooter': 5.00
    };

    const GPS_FEE = 5.00;
    const INSURANCE_FEE = 8.50;
    const DAILY_DISCOUNT_FACTOR = 0.8;

    const vehicleTypeInput = document.getElementById('vehicleType');
    const durationInput = document.getElementById('durationMinutes');
    const gpsCheckbox = document.getElementById('addGps');
    const insuranceCheckbox = document.getElementById('addInsurance');
    const estimatedCostSpan = document.getElementById('estimatedCost');
    const finalCostHiddenInput = document.getElementById('finalCostHidden');

    function calculateEstimatedCost() {
        const vehicleType = vehicleTypeInput.value;
        const duration = parseInt(durationInput.value) || 0;
        const hourlyBaseRate = VEHICLE_BASE_COSTS[vehicleType] || 0;

        let rentalCost;

        if (duration < 60) {
            let ratePerMinute = hourlyBaseRate / 60;
            rentalCost = ratePerMinute * duration;
        } else if (duration < 1440) {
            let hours = Math.ceil(duration / 60);
            rentalCost = hours * hourlyBaseRate;
        } else {
            let days = Math.ceil(duration / 1440);
            let dailyRate = hourlyBaseRate * 24 * DAILY_DISCOUNT_FACTOR;
            rentalCost = days * dailyRate;
        }

        if (gpsCheckbox && gpsCheckbox.checked) {
            rentalCost += GPS_FEE;
        }
        if (insuranceCheckbox && insuranceCheckbox.checked) {
            rentalCost += INSURANCE_FEE;
        }

        estimatedCostSpan.textContent = rentalCost.toFixed(2);
        if (finalCostHiddenInput) {
            finalCostHiddenInput.value = rentalCost.toFixed(2);
        }
    }

    durationInput.addEventListener('input', calculateEstimatedCost);
    if (gpsCheckbox) gpsCheckbox.addEventListener('change', calculateEstimatedCost);
    if (insuranceCheckbox) insuranceCheckbox.addEventListener('change', calculateEstimatedCost);

    calculateEstimatedCost();
});