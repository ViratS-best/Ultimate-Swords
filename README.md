DOWNLOAD MOD HERE:
# https://modrinth.com/mod/ultimateswords
OR:
# https://github.com/ViratS-best/Ultimate-Swords/blob/main/ultimate_swords-1.0.0.jar
# Ultimate Swords

## Overview

Ultimate Swords is a Fabric-based Minecraft mod that introduces a collection of powerful and unique custom swords into the game. These swords are designed to offer distinct gameplay experiences and combat advantages, pushing the boundaries of what a melee weapon can be in Minecraft.

## IMPORTANT: No Enchantments!

A core design principle of Ultimate Swords is the **complete absence of traditional Minecraft enchantments** on any of our custom swords. All inherent abilities and power of these weapons come from their base statistics and unique built-in properties. This decision was made to simplify the power curve, encourage strategic use of specific sword types, and provide a fresh combat experience unreliant on the enchantment system.

You will **not** be able to apply vanilla enchantments (like Sharpness, Looting, Unbreaking, Mending, etc.) to any sword added by this mod.

## Installation & Setup (For Developers)

If you wish to contribute to Ultimate Swords or set up a development environment:

### Prerequisites

* **Java Development Kit (JDK) 21 or newer:** Ensure you have JDK 21 installed and configured on your system.
* **Git:** For cloning the repository and version control.
* **A text editor or IDE:** Such as IntelliJ IDEA (recommended for Fabric development) or VS Code.

### Getting Started

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/ViratS-best/Ultimate-Swords.git](https://github.com/ViratS-best/Ultimate-Swords.git)
    cd Ultimate-Swords
    ```

2.  **Setup Development Environment:**
    The project uses Gradle for dependency management and building.
    ```bash
    ./gradlew cleanEclipse # If using Eclipse
    ./gradlew cleanIdea   # If using IntelliJ IDEA
    ./gradlew genSources  # Generates source code for dependencies
    ./gradlew build       # Builds the project
    ```
    If you're using IntelliJ IDEA, import the project as a Gradle project.

3.  **Run Client (for testing):**
    This command will launch a Minecraft client with your mod loaded.
    ```bash
    ./gradlew runClient
    ```
    If you encounter `Plugin [id: 'fabric-loom', version: '1.7.0'] was not found` or similar errors, ensure you have a stable internet connection and try cleaning Gradle caches:
    ```bash
    ./gradlew --stop
    rm -rf ~/.gradle/
    rm -rf build/ .gradle/
    ./gradlew runClient --refresh-dependencies --info --debug
    ```

## Building for Release

To create a distributable `.jar` file of your mod:

```bash
./gradlew build
