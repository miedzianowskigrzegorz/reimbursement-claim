/**
 * Script handling the user interface and interactions for creating a reimbursement claim.
 * Manages input data, displays results, and communicates with the server to create claims.
 * Provides functions for handling trip dates, receipts, allowances, mileage, and claim creation.
 *
 * @version 1.0
 * @since 2023-08-14
 */

                                        //Content Sector

document.addEventListener("DOMContentLoaded", function() {
    loadReceiptTypes();
    loadReimbursementRatesSetting();
    loadReimbursementCostLimit();
    loadMileageLimit();
});

const createdReimbursementElement = document.getElementById("createdReimbursement");
const totalReimbursementAmount = document.getElementById("totalReimbursementAmount");
const personalCarMileage = document.getElementById("personalCarMileage");
personalCarMileage.style.display = "none";

const reimbursementClaim = {
    tripDate: null,
    receipts: [],
    periods: [],
    personalCarMileage: null,
    totalCost: null,
};

let receiptTypes = {
    types: [],
};

let dailyAllowanceRate;
let mileageRate;
let reimbursementCostLimit;
let mileageLimit;



                                         // Date Sector

function addTripDate() {
    if (reimbursementClaim.tripDate != null) {
        alert("You added a date already.");
        return;
    }

    const tripDateText = document.getElementById("tripDate").value;

    if (!isValidDate(tripDateText)) {
        alert("Please enter a valid date.");
        return;
    }

    const tripDateValue = new Date(tripDateText);
    const formattedDate = formatDateForJavaLocalDate(tripDateValue);
    reimbursementClaim.tripDate = formattedDate;
    updateTripDate();
}

function isValidDate(dateString) {
    const datePattern = /^\d{4}-\d{2}-\d{2}$/;
    return datePattern.test(dateString) && !isNaN(new Date(dateString));
}

function formatDateForJavaLocalDate(date) {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function updateTripDate() {
    const tripDateDisplay = document.getElementById("tripDateValue");

    if (reimbursementClaim.tripDate != null) {
        const formattedDate = reimbursementClaim.tripDate;
        tripDateDisplay.textContent = formattedDate;
    } else {
        tripDateDisplay.textContent = "No date added.";
    }
}

                                       // Receipt Sector

function addReceipt() {
    const receiptType = document.getElementById("receiptType").value;
    const receiptAmount = parseFloat(document.getElementById("receiptAmount").value);
    const selectedReceiptType = receiptTypes.types.find(type => type.type === receiptType);

    if (!isNaN(receiptAmount) && receiptAmount > 0 && receiptAmount <= selectedReceiptType.limit) {
        reimbursementClaim.receipts.push({ type: receiptType, amount: receiptAmount });
        reimbursementClaim.totalCost += receiptAmount;
        updateTotalReimbursement();
        updateReceiptsList();
    } else if (receiptAmount > selectedReceiptType.limit) {
        alert(`The receipt amount exceeds the limit of ${selectedReceiptType.limit}.`);
    } else {
        alert("Invalid receipt amount.");
    }
}

function updateReceiptsList() {
    const receiptsList = document.getElementById("receiptsList");
    receiptsList.innerHTML = "";

    reimbursementClaim.receipts.forEach(receipt => {
        const listItem = document.createElement("li");
        listItem.textContent = `${receipt.type}: ${receipt.amount}`;
        receiptsList.appendChild(listItem);
    });
}

function loadReceiptTypes() {
    fetch('/getReceiptTypes')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            receiptTypes.types = data;
            const selectElement = document.getElementById("receiptType");
            selectElement.innerHTML = '';

            receiptTypes.types.forEach(receiptType => {
                const option = document.createElement("option");
                option.value = receiptType.type;
                option.textContent = `${receiptType.type} (Limit: ${receiptType.limit})`;
                selectElement.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

                                      //Daily Allowance Sector
function addDailyAllowance() {
    if (reimbursementClaim.periods.length >= 1) {
        alert("You can add up to 1 period.");
        return;
    }

    const days = parseInt(document.getElementById("days").value);

    if (!isNaN(days) && days > 0) {
        reimbursementClaim.periods.push({ days });
        const disableCheckbox = document.getElementById('disableCheckbox');

        if (!disableCheckbox.checked) {
            reimbursementClaim.totalCost += days * dailyAllowanceRate;
        }

        updateTotalReimbursement();
        updateDailyAllowanceList();
        dailyAllowanceAdded = true;
    }
}

function updateDailyAllowanceList() {
    const dailyAllowanceList = document.getElementById("dailyAllowanceList");
    dailyAllowanceList.innerHTML = "";

    reimbursementClaim.periods.forEach(period => {
        const listItem = document.createElement("li");
        listItem.textContent = `Daily Allowance (${period.days} days): ${period.days * dailyAllowanceRate}`;
        dailyAllowanceList.appendChild(listItem);
    });
}

                                      //Car Mileage Sector

function addPersonalCarMileage() {
    const mileage = parseFloat(document.getElementById("mileage").value);

    if(mileage > mileageLimit.mileageLimit) {
                alert(`The mileage distance exceeds the limit of ${mileageLimit.mileageLimit}.`);
                return;
    }

    if (reimbursementClaim.personalCarMileage != null) {
        alert("You can add up to 1 personal car mileage.");
        return;
    }

    if (!isNaN(mileage) && mileage > 0) {
        reimbursementClaim.personalCarMileage = mileage;
        reimbursementClaim.totalCost += mileage * mileageRate;
        updatePersonalCarMileage();
        updateTotalReimbursement();
    }
}

function updatePersonalCarMileage() {
    const personalCarMileageValue = document.getElementById("personalCarMileageValue");
    personalCarMileageValue.textContent = `Car mileage (${reimbursementClaim.personalCarMileage} km): ${reimbursementClaim.personalCarMileage * mileageRate}`;

}

function personalCarMileageTrue() {
    document.getElementById("personalCarMileageQuestion").style.display = "none";
    personalCarMileage.style.display = "block";
}

function personalCarMileageFalse() {
    document.getElementById("personalCarMileageQuestion").style.display = "none";
    personalCarMileage.style.display = "none";
}

function loadMileageLimit() {
    fetch('/getTotalMileageLimitSetting')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            mileageLimit = data;
            console.log("Mileage distance limit loaded.");
            console.log('Limit:', mileageLimit.mileageLimit);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

                                      //Reimbursement Sector

function createReimbursementClaim() {

    if(reimbursementClaim.totalCost > reimbursementCostLimit.totalReimbursementLimit) {
            alert(`The total cost exceeds the limit of ${reimbursementCostLimit.totalReimbursementLimit}.`);
            return;
    }

    if (reimbursementClaim.receipts.length === 0 || reimbursementClaim.periods.length === 0 || reimbursementClaim.tripDate === null ) {
        createdReimbursementElement.textContent = "Date,receipts and/or periods are empty. Please fill all required fields.";
        return;
    }

    console.info("Creating reimbursement claim:" + JSON.stringify(reimbursementClaim));

    fetch('/createReimbursement', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reimbursementClaim)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        createdReimbursementElement.textContent = "Created successfully.";
        console.log('Response from backend:', JSON.stringify(data));
    })
    .catch(error => {
        console.error('Error:', error);
    });

}

function loadReimbursementRatesSetting() {
    fetch('/getReimbursementRatesSetting')
        .then(response => response.json())
        .then(data => {
            dailyAllowanceRate = data.dailyAllowanceRate;
            mileageRate = data.mileageRate;

            console.log("Rates Setting loaded.");
            console.log('Daily Allowance Rate:', dailyAllowanceRate);
            console.log('Mileage Rate:', mileageRate);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function updateTotalReimbursement() {
    totalReimbursementAmount.textContent = reimbursementClaim.totalCost;
}

function loadReimbursementCostLimit() {
    fetch('/getTotalReimbursementCostLimitSetting')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            reimbursementCostLimit = data;
            console.log("Reimbursement cost limit loaded.");
            console.log('Limit:', reimbursementCostLimit.totalReimbursementLimit);

        })
        .catch(error => {
            console.error('Error:', error);
        });
}


