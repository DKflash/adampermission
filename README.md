<h1 align="center">PermissionsManager Android Library</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Android-PermissionsManager-brightgreen" alt="PermissionsManager Android Library">
</p>

<p align="center">
  This Android library simplifies handling dynamic permissions. It manages permission requests, status updates, and user interactions with permissions using a custom-built library.
</p>

---

<h2>📋 Table of Contents</h2>

<ul>
  <li><a href="#features">Features</a></li>
  <li><a href="#technologies">Technologies</a></li>
  <li><a href="#permissions">Permissions</a></li>
  <li><a href="#installation">Installation</a></li>
  <li><a href="#usage">Usage</a></li>
  <li><a href="#contributing">Contributing</a></li>
</ul>

---

<h2 id="features">✨ Features</h2>

<ul>
  <li><strong>Dynamic Permission Handling</strong>: Request Android permissions at runtime, ensuring a smooth user experience.</li>
  <li><strong>Permission Status Updates</strong>: Automatically update and display the status of requested permissions.</li>
  <li><strong>Custom Permission Management Library</strong>: Encapsulates the permission handling logic in reusable classes.</li>
  <li><strong>RecyclerView Interface</strong>: Presents permissions and their statuses in a user-friendly RecyclerView interface.</li>
</ul>

---

<h2 id="technologies">💻 Technologies</h2>

<ul>
  <li><strong>Android SDK</strong>: The core platform for Android development.</li>
  <li><strong>Java</strong>: The programming language used for building this library.</li>
  <li><strong>RecyclerView</strong>: Displays permissions and their statuses in a list format.</li>
  <li><strong>Gradle</strong>: Manages dependencies and builds the project.</li>
</ul>

---

<h2 id="permissions">🔐 Permissions</h2>

<p>The application requests the following permissions:</p>

<ul>
  <li>INTERNET</li>
  <li>CALL_PHONE</li>
  <li>VIBRATE</li>
  <li>RECORD_AUDIO</li>
  <li>ACCESS_FINE_LOCATION</li>
  <li>ACCESS_COARSE_LOCATION</li>
  <li>READ_SMS</li>
  <li>READ_CONTACTS</li>
  <li>CAMERA</li>
  <li>READ_CALENDAR</li>
  <li>BODY_SENSORS</li>
  <li>BLUETOOTH_SCAN</li>
</ul>

<p>These permissions are managed dynamically using custom <code>PermissionHelper</code> and <code>PermissionHandler</code> classes.</p>

---

<h2 id="installation">🚀 Installation</h2>

<p>To include the PermissionsManager library in your Android project:</p>

<pre><code>
1. Clone the repository:
   <code>git clone https://github.com/Adam-Agbaria/Permission-Manager-Library.git</code>

2. Add the necessary permissions to your AndroidManifest.xml file.

3. Add the permissionslib folder as a module in your Android project.
</code></pre>

---

<h2 id="usage">📚 Usage</h2>

<p>Here's how to use the PermissionsManager library in your app:</p>

<h3>1. Initialize Permission Items</h3>

```java
private void initializePermissionItems() {
    String[] permissionTitlesArray = getResources().getStringArray(R.array.app_permissions_txt);
    for (int i = 0; i < permissionTitlesArray.length; i++) {
        permissionItemList.add(new PermissionItem(permissionTitlesArray[i], permissionIcons[i]));
    }
}
```
<h3>2. Request Permissions</h3>

```java
private void requestAllDeclaredPermissions() {
    String[] declaredPermissions = PermissionHandler.getInstance().getAppDeclaredPermissions(this);
    if (!PermissionHandler.getInstance().checkPermissions(this, declaredPermissions)) {
        PermissionHandler.getInstance().requestMissingPermissions(this, declaredPermissions, PERMISSION_CODE_REQUEST);
    }
}
```
<h3>3. Refresh Permission Status</h3>

```java
private void refreshPermissionStatus() {
    for (PermissionItem permissionItem : permissionItemList) {
        if (PermissionHandler.getInstance().isPermissionAllowed(this, "android.permission." + permissionItem.getPermissionTitle())) {
            permissionItem.setPermissionGranted(true);
        } else {
            permissionItem.setPermissionGranted(false);
        }
    }
    permissionAdapter.notifyDataSetChanged();
}
```

<h2 id="contributing">🤝 Contributing</h2>
<p>We welcome contributions to enhance the PermissionsManager library! To contribute:</p>
<ol> <li>Fork the repository.
</li> <li>Create a new branch for your feature or bugfix.</li>
<li>Submit a pull request with a description of your changes.</li> </ol>
<h2 align="center">💡 Happy Coding!</h2> ```

https://github.com/user-attachments/assets/e9fd23a2-68f9-4de9-b894-e7e4ebed5544



