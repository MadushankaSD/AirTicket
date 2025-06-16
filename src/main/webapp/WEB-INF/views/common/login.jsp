<div id="loginModal" style="
    display: none;
    position: fixed;
    z-index: 1000;
    left: 0; top: 0; width: 100%; height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.5);">
    <div style="
      background-color: white;
      margin: 10% auto;
      padding: 20px 30px 30px;
      border-radius: 8px;
      max-width: 400px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.3);
      position: relative;
      font-family: 'Segoe UI', sans-serif;">
    <span onclick="closeLoginModal()" style="
        position: absolute;
        right: 15px; top: 10px;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
        user-select: none;">&times;</span>

        <h2 style="margin-top: 0; margin-bottom: 20px; color: #004080;">Login</h2>
        <div id="loginError" style="color: red; margin-bottom: 10px; font-weight: bold;"></div>
        <form id="loginForm" onsubmit="return submitLogin(event)">
            <div style="margin-bottom: 20px;">
                <label for="modalUsername" style="display: block; margin-bottom: 6px;">Username</label>
                <input type="text" id="modalUsername" name="username" required
                       style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;"/>
            </div>
            <div style="margin-bottom: 10px;">
                <label for="modalPassword" style="display: block; margin-bottom: 6px;">Password</label>
                <input type="password" id="modalPassword" name="password" required
                       style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;"/>
            </div>

            <div style="margin-bottom: 20px; font-size: 14px;">
                <input type="checkbox" id="showPassword" onclick="togglePassword()"/>
                <label for="showPassword" style="cursor: pointer; user-select: none;">Show Password</label>
            </div>

            <button type="submit" style="background-color: #007bff;
                color: white;
                border: none;
                padding: 12px 20px;
                border-radius: 6px;
                font-weight: 600;
                cursor: pointer;
                width: 100%;
                font-size: 16px;
                transition: background-color 0.3s ease;
                " onmouseover="this.style.backgroundColor='#0056b3'" onmouseout="this.style.backgroundColor='#007bff'">
                Log In
            </button>
        </form>
    </div>
</div>

<script>

    async function logout(event){
        event.preventDefault();
        try {
            const response = await fetch("${pageContext.request.contextPath}/logout", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            });

            const result = await response.json();

            if (result.success) {
                window.location.href = "${pageContext.request.contextPath}/home";
            } else {
                errorDiv.innerText = result.message;
            }
        } catch (e) {
            console.error("Login error", e);
            document.getElementById("errorDiv").innerText = result.message;
        }

    }

    function openLoginModal(event) {
        event.preventDefault();
        document.getElementById('loginModal').style.display = 'block';
    }

    function closeLoginModal() {
        document.getElementById('loginModal').style.display = 'none';
    }

    function togglePassword() {
        const pwInput = document.getElementById('modalPassword');
        pwInput.type = pwInput.type === 'password' ? 'text' : 'password';
    }

    async function submitLogin(event) {
        event.preventDefault();

        const username = document.getElementById('modalUsername').value.trim();
        const password = document.getElementById('modalPassword').value.trim();
        const errorDiv = document.getElementById('loginError');

        if (!username || !password) {
            errorDiv.innerText = "Please enter both username and password.";
            return false;
        }

        try {
            const response = await fetch("${pageContext.request.contextPath}/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: new URLSearchParams({
                    username: username,
                    password: password
                })
            });

            const result = await response.json();

            if (result.success) {
                closeLoginModal();
                window.location.href = "${pageContext.request.contextPath}/home";
            } else {
                errorDiv.innerText = result.message;
            }
        } catch (e) {
            console.error("Login error", e);
            errorDiv.innerText = "Something went wrong. Try again later.";
        }

        return false;
    }
</script>


