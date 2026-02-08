const API_URL = "http://localhost:8080/api";
let currentAccount = null; // Stores the logged-in user data

// ================= AUTH FUNCTIONS =================
async function login() {
    const accNo = document.getElementById("loginAccNo").value;
    if (!accNo) return showMessage("Enter Account Number", "error");

    try {
        const response = await fetch(`${API_URL}/balance/${accNo}`);
        const data = await response.text();

        // If backend returns empty string or error logic (depends on your backend return type)
        // Since we return 'null' from Java if not found, we check if response is valid JSON
        if (!data) throw new Error("Account not found");

        const account = JSON.parse(data); // Parse manual JSON if needed

        currentAccount = account;
        enterDashboard();
    } catch (e) {
        showMessage("Account Not Found! Please Register.", "error");
    }
}

function enterDashboard() {
    document.getElementById("login-section").classList.add("hidden");
    document.getElementById("dashboard-section").classList.remove("hidden");
    updateUI();
}

function logout() {
    currentAccount = null;
    document.getElementById("login-section").classList.remove("hidden");
    document.getElementById("dashboard-section").classList.add("hidden");
    document.getElementById("loginAccNo").value = ""; // Clear input for safety
    showMessage("Logged out securely", "success");
}

function updateUI() {
    document.getElementById("userGreeting").innerText = currentAccount.customerName;
    document.getElementById("dashBalance").innerText = "$" + currentAccount.balance.toFixed(2);
    document.getElementById("dashAccNo").innerText = currentAccount.accountNumber;
    document.getElementById("dashType").innerText = currentAccount.accountType;

    // Update Profile Section too
    document.getElementById("pName").innerText = currentAccount.customerName;
    document.getElementById("pAccNo").innerText = currentAccount.accountNumber;
    document.getElementById("pType").innerText = currentAccount.accountType;
}

// ================= ACTION HANDLERS =================
function showSection(section) {
    // Hide all forms
    document.querySelectorAll('.action-form').forEach(el => el.classList.add('hidden'));

    // Show selected
    document.getElementById(`form-${section}`).classList.remove('hidden');

    // Update Title
    const titles = {
        'deposit': 'Deposit Money',
        'withdraw': 'Withdraw Money',
        'transfer': 'Transfer Funds',
        'profile': 'Account Profile'
    };
    document.getElementById("actionTitle").innerText = titles[section];
}

async function performTransaction(type) {
    let url = "";
    let params = "";

    if (type === 'deposit') {
        const amt = document.getElementById("depAmount").value;
        url = `${API_URL}/deposit?accNo=${currentAccount.accountNumber}&amount=${amt}`;
    }
    else if (type === 'withdraw') {
        const amt = document.getElementById("wdAmount").value;
        url = `${API_URL}/withdraw?accNo=${currentAccount.accountNumber}&amount=${amt}`;
    }
    else if (type === 'transfer') {
        const target = document.getElementById("targetAcc").value;
        const amt = document.getElementById("tfAmount").value;
        url = `${API_URL}/transfer?fromAcc=${currentAccount.accountNumber}&toAcc=${target}&amount=${amt}`;
    }

    const response = await fetch(url, { method: 'POST' });
    const msg = await response.text();

    if (msg.includes("Success")) {
        showMessage(msg, "success");
        refreshData(); // Reload balance
    } else {
        showMessage(msg, "error");
    }
}

async function createAccount() {
    const name = document.getElementById("regName").value;
    const accNo = document.getElementById("regAccNo").value;
    const bal = document.getElementById("regBalance").value;
    const type = document.getElementById("regType").value;

    const response = await fetch(`${API_URL}/create?name=${name}&accNo=${accNo}&balance=${bal}&type=${type}`, { method: 'POST' });
    const msg = await response.text();

    if(msg.includes("Success")) {
        showMessage("Account Created! You can now login.", "success");
        // Optional: clear inputs
    } else {
        showMessage(msg, "error");
    }
}

async function deleteAccount() {
    if(!confirm("Are you sure? This action cannot be undone!")) return;

    const response = await fetch(`${API_URL}/delete/${currentAccount.accountNumber}`, { method: 'DELETE' });
    const msg = await response.text();

    if(msg.includes("Success")) {
        alert("Account Deleted.");
        logout();
    } else {
        showMessage(msg, "error");
    }
}

async function refreshData() {
    const response = await fetch(`${API_URL}/balance/${currentAccount.accountNumber}`);
    currentAccount = await response.json();
    updateUI();
}

function showMessage(msg, type) {
    const box = document.getElementById("messageBox");
    box.innerText = msg;
    box.className = `message-box ${type === 'success' ? 'msg-success' : 'msg-error'}`;
    box.style.display = 'block';
    setTimeout(() => box.style.display = 'none', 3000);
}