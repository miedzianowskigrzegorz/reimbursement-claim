/**
 * Script handling interactions related to travel expense management.
 * Contains functions for loading and saving settings and receipt types.
 * Also provides functions for updating cost and mileage limits.
 *
 * @version 1.0
 * @since 2023-08-14
 */

                                        //Content Sector

document.addEventListener('DOMContentLoaded', () => {
    loadReimbursementRatesSetting();
    loadReceiptTypes();
    loadReimbursementCostLimit();
    loadMileageLimit();
});

const dailyAllowanceRateElement = document.getElementById("dailyAllowanceRateActual");
const mileageRateElement = document.getElementById("mileageRateActual");
let dailyAllowanceRate;
let mileageRate;

let reimbursementCostLimit;
let mileageLimit;

const receiptTypes = {
    types: [],
};

                                        //Reimbursement Rates Sector

function loadReimbursementRatesSetting() {
    fetch('/getReimbursementRatesSetting')
        .then(response => response.json())
        .then(data => {
            dailyAllowanceRate = data.dailyAllowanceRate;
            mileageRate = data.mileageRate;
            dailyAllowanceRateElement.innerHTML = dailyAllowanceRate;
            mileageRateElement.innerHTML = mileageRate;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function saveRates() {
    const updatedDailyAllowanceRate = parseFloat(document.getElementById("dailyAllowanceRate").value);
    const updatedMileageRate = parseFloat(document.getElementById("mileageRate").value);

    if (isNaN(updatedDailyAllowanceRate) || isNaN(updatedMileageRate)) {
            alert('Please enter valid numbers in both fields.');
            return;
    }

    if (updatedDailyAllowanceRate <= 0  || updatedMileageRate <= 0) {
            alert('Please enter positive numbers in both fields.');
            return;
    }

    const roundedDailyAllowanceRate = parseFloat(updatedDailyAllowanceRate.toFixed(2));
    const roundedMileageRate = parseFloat(updatedMileageRate.toFixed(2));

    dailyAllowanceRate = roundedDailyAllowanceRate;
    mileageRate = roundedMileageRate;

    dailyAllowanceRateElement.innerHTML = dailyAllowanceRate;
    mileageRateElement.innerHTML = mileageRate;

    const updatedRates = {
        dailyAllowanceRate: dailyAllowanceRate,
        mileageRate: mileageRate
    };

    console.log("data:" + updatedRates.dailyAllowanceRate + " " + updatedRates.mileageRate);

    fetch('/updateReimbursementRatesSetting', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedRates)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Response from backend:', JSON.stringify(data));
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while communicating with the server. Please try again later.');
    });
}

                                        //Receipts Sector

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
            updateReceiptTypes();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function addReceiptType() {
    const newReceiptType = document.getElementById("newReceiptType").value.trim();
    const receiptLimit = parseFloat(document.getElementById("receiptLimit").value);

    if (newReceiptType === "") {
        alert("Receipt type cannot be empty.");
        return;
    }

    if (isNaN(receiptLimit) || receiptLimit <= 0) {
        alert("Receipt limit must be a valid number greater than 0.");
        return;
    }

    receiptTypes.types.push({ type: newReceiptType, limit: receiptLimit });
    updateReceiptTypes();
}

function updateReceiptTypes() {
    const receiptTypeElement = document.getElementById("receiptsTypeList");
    receiptTypeElement.innerHTML = ''; // Wyczyść poprzednie elementy na liście

    receiptTypes.types.forEach(type => {
        const listItem = document.createElement("li");
        listItem.textContent = `${type.type} (Limit: ${type.limit})`;

        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete";
        deleteButton.addEventListener("click", () => {
            const index = receiptTypes.types.indexOf(type);
            if (index !== -1) {
                receiptTypes.types.splice(index, 1);
                listItem.remove();
                console.log("Removed type: " + type);
            }
        });

        listItem.appendChild(deleteButton);
        receiptTypeElement.appendChild(listItem);
    });
}

function saveReceiptTypes() {
    console.log("Data to send:" + JSON.stringify(receiptTypes));

    if( receiptTypes.types.length === 0) {
        alert("How can we get a refund for a business trip if we can't choose a receipt?");
    }

    fetch('/updateReceiptTypes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(receiptTypes)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Response from backend:', JSON.stringify(data));
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while communicating with the server. Please try again later.');
    });
}

                                        //Total Reimbursement Limit Sector

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
            updateReimbursementCostLimit();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function saveReimbursementCostLimit() {
    const newReimbursementLimit = parseFloat(document.getElementById("newReimbursementCostLimit").value);

    if (isNaN(newReimbursementLimit) || newReimbursementLimit <= 0) {
        alert("Please enter a valid number greater than 0 for the reimbursement limit.");
        return;
    }

    reimbursementCostLimit.totalReimbursementLimit = newReimbursementLimit;
    updateReimbursementCostLimit();

    fetch('/updateTotalReimbursementCostLimitSetting', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reimbursementCostLimit)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Response from backend:', JSON.stringify(data));
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while communicating with the server. Please try again later.');
        });


}

function updateReimbursementCostLimit() {
    const reimbursementLimit = document.getElementById("totalReimbursementLimit");
    reimbursementLimit.innerHTML = '';
    reimbursementLimit.textContent = `${reimbursementCostLimit.totalReimbursementLimit}`;
}

                                        //Mileage Distance Sector

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
            updateMileageLimit();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function updateMileageLimit() {
    const updatedMileageLimit = document.getElementById("mileageLimit");
    updatedMileageLimit.innerHTML = '';
    updatedMileageLimit.textContent = `${mileageLimit.mileageLimit}`;
}

function saveMileageLimit() {
    const newMileageLimit = parseFloat(document.getElementById("newMileageLimit").value);

    if (isNaN(newMileageLimit) || newMileageLimit <= 0) {
        alert("Please enter a valid number greater than 0 for the mileage limit.");
        return;
    }

    mileageLimit.mileageLimit = newMileageLimit;
    updateMileageLimit();

    fetch('/updateTotalMileageLimitSetting', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(mileageLimit)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Response from backend:', JSON.stringify(data));
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while communicating with the server. Please try again later.');
        });
}





