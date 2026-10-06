# NetQuest: Pruebas Automatizadas Mobile Web con Appium, Cucumber y Java

Proyecto de automatización de pruebas de la web móvil de [Nicequest](https://www.nicequest.com/es) ejecutadas en Chrome sobre un emulador Android. Utiliza Appium (UiAutomator2), Cucumber (BDD) y TestNG, siguiendo el patrón Page Object.

Actualmente cubre el flujo de **login**: login correcto y login con contraseña inválida.

> **Nota:** el proyecto prueba un sitio de terceros. Las credenciales **no se incluyen** en el repositorio y deben pasarse por parámetro al ejecutar.

---

## Tabla de Contenidos

1. [Tecnologías](#tecnologías)
2. [Requisitos Previos](#requisitos-previos)
3. [Instalación](#instalación)
4. [Configuración](#configuración)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Cómo Ejecutar las Pruebas](#cómo-ejecutar-las-pruebas)
7. [Reportes y Capturas de Pantalla](#reportes-y-capturas-de-pantalla)
8. [Escenarios Cubiertos](#escenarios-cubiertos)
9. [Decisiones de Diseño](#decisiones-de-diseño)

---

## Tecnologías

| Herramienta | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje |
| Maven | 3.9+ | Gestión de dependencias y ejecución |
| Appium Java Client | 10.1.1 | Control del dispositivo |
| Selenium | 4.50.0 | API de WebDriver y esperas |
| Cucumber (Java, TestNG, PicoContainer) | 7.33.0 | BDD e inyección de dependencias |
| TestNG | 7.12.0 | Motor de ejecución y aserciones |
| Appium Server + UiAutomator2 | 2.x | Automatización en Android |

---

## Requisitos Previos

Asegúrate de tener instalado:

- [JDK 21](https://adoptium.net/) (con `JAVA_HOME` configurado)
- [Maven](https://maven.apache.org/) 3.9 o superior
- [Node.js](https://nodejs.org/) (necesario para Appium)
- [Appium 2](https://appium.io/) con el driver UiAutomator2
- [Android Studio](https://developer.android.com/studio) con un emulador (AVD) creado, preferiblemente con una imagen **Google APIs / Google Play** reciente para que Chrome esté actualizado

Para verificar las instalaciones:

```bash
java -version
mvn -version
node -v
appium -v
adb devices
```

---

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/sndivad/NetQuest.git
   ```

2. Entra al directorio del proyecto:
   ```bash
   cd NetQuest
   ```

3. Instala el driver de Appium (solo la primera vez):
   ```bash
   appium driver install uiautomator2
   ```

4. Descarga las dependencias de Maven:
   ```bash
   mvn clean install -DskipTests
   ```

---

## Configuración

### Fichero `config.properties`

Ubicado en `src/test/resources/config.properties`:

```properties
base.url=https://www.nicequest.com/es
appium.url=http://127.0.0.1:4723
android.udid=emulator-5554
```

Cualquier propiedad puede sobrescribirse por línea de comandos con `-Dclave=valor`, que tiene prioridad sobre el fichero.

### Parámetros de ejecución

| Parámetro | Obligatorio | Descripción | Valor por defecto |
|---|---|---|---|
| `user.email` | Sí | Email de un usuario válido | - |
| `user.password` | Sí | Contraseña del usuario | - |
| `platform` | No | Plataforma de ejecución | `android` |
| `android.udid` | No | Identificador del dispositivo | `emulator-5554` |

> Nunca subas credenciales al repositorio.

---

## Estructura del Proyecto

```
NetQuest/
├── pom.xml                                   # Dependencias y configuración de Maven/Surefire
├── README.md                                 # Documentación del proyecto
└── src/
    └── test/
        ├── java/
        │   ├── org/sndivad/selenium/
        │   │   ├── config/
        │   │   │   └── DriverManager.java    # Creación y cierre del driver de Appium
        │   │   ├── pages/
        │   │   │   ├── LoginPage.java        # Page Object de la pantalla de login
        │   │   │   └── HomePage.java         # Page Object de la home
        │   │   ├── steps/
        │   │   │   ├── Hooks.java            # @Before / @After y captura en fallo
        │   │   │   ├── LoginSteps.java       # Steps del login
        │   │   │   └── HomeSteps.java        # Steps de la home
        │   │   └── utils/
        │   │       └── PropertyReader.java   # Lectura de propiedades (-D > fichero)
        │   └── runners/
        │       └── TestRunner.java           # Runner de Cucumber con TestNG
        └── resources/
            ├── config.properties             # Configuración del entorno
            └── features/
                └── login.feature             # Escenarios BDD
```

Tras ejecutar las pruebas se genera además:

```
target/
├── cucumber-report.html       # Reporte de Cucumber (con capturas de los fallos)
└── surefire-reports/          # Informes de Surefire
```

---

## Cómo Ejecutar las Pruebas

### 1. Arrancar el emulador

Desde Android Studio (Device Manager) o por línea de comandos. Comprueba que está disponible:

```bash
adb devices
```

Debe aparecer `emulator-5554` con estado `device`.

### 2. Arrancar el servidor de Appium

En una terminal aparte:

```bash
appium --allow-insecure=chromedriver_autodownload
```

La opción permite a Appium descargar automáticamente el chromedriver compatible con la versión de Chrome del emulador.

### 3. Ejecutar las pruebas

Desde la raíz del proyecto:

```bash
mvn clean test -Duser.email=tu_email@ejemplo.com -Duser.password=tu_password
```

Para indicar plataforma o dispositivo explícitamente:

```bash
mvn clean test -Dplatform=android -Dandroid.udid=emulator-5554 -Duser.email=... -Duser.password=...
```

También pueden lanzarse desde IntelliJ IDEA ejecutando `TestRunner` (añade las propiedades en *VM options*: `-Duser.email=... -Duser.password=...`).


---

## Reportes y Capturas de Pantalla

Al finalizar la ejecución se genera el reporte HTML de Cucumber en:

```
target/cucumber-report.html
```

Ábrelo con cualquier navegador para ver el detalle de cada escenario y step.

**Captura en fallo:** cuando un escenario falla, el hook `@After` adjunta automáticamente una captura de pantalla al reporte. La captura se obtiene cambiando temporalmente al contexto `NATIVE_APP`, ya que en una sesión web de Chrome el screenshot a través de chromedriver puede devolver `null`.

---

## Escenarios Cubiertos

Definidos en `src/test/resources/features/login.feature`:

| Escenario | Resultado esperado |
|---|---|
| Login con email y contraseña válidos | Se muestra la home (botón de perfil visible) |
| Login con email válido y contraseña inválida | Se muestra el mensaje de error de login |

Ambos comparten un `Background` que abre la web, despliega el menú y muestra el formulario de login.

---

![Mi imagen desde Imgur](https://i.imgur.com/5IBeNir.jpeg)
