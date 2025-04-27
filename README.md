# Discord Command Plugin Documentation & Setup Guide

## Overview
The **Discord Command** plugin allows Minecraft server admins to easily share a Discord invite link with players, as well as modify and reload the plugin’s configuration on the fly. This guide will walk you through the installation, configuration, and usage of the plugin.

---

## Installation

### 1. **Download the Plugin**
Download the latest version of the **Discord Command** plugin from the provided link or from the plugin repository.

### 2. **Place the Plugin in the Plugins Folder**
- Upload the plugin `.jar` file to your server's `plugins` folder.
- Restart or reload your server to load the plugin.

### 3. **Check Plugin Activation**
After restarting, check the console or in-game logs for the message:  
`Plugin activated!`

---

## Configuration

### 1. **Configuration File**
The plugin automatically generates a `config.yml` file in the `plugins/CommandDiscord` folder.  
This file contains key settings that control the Discord link shown to users.

### 2. **Edit the Discord Link**
Open the `config.yml` file in any text editor, and you'll find a line like this:

```yaml
link: "your-discord-invite-link"
```
### 3. Set Permissions
   To allow specific players or groups to use the commands like /discord edit or /discord reload, set up the following permissions in your permissions plugin (e.g., PermissionsEx, LuckPerms):

**discord.admin**: Grants permission to use /discord reload and /discord edit.


## Usage
### 1. Commands Overview
   **/discord**
   Displays the Discord link configured in config.yml.
   Example usage:
   /discord → Shows the Discord link.

**/discord reload**
Reloads the plugin’s configuration without needing to restart the server.
Example usage:
/discord reload → Reloads the config file.

**/discord edit <key> <value>**
Allows server admins to edit specific configuration values dynamically.
Example usage:
/discord edit link https://discord.gg/anotherlink → Changes the Discord link to a new value.

### 2. Tab-Completion
   The plugin supports tab-completion for the following commands:

**/discord reload**

**/discord edit**

Admins can easily see the available subcommands and keys by typing part of the command and pressing Tab.

## Troubleshooting
### 1. Command Not Working
   Ensure you have the correct permissions set up for your user or group to use the commands (especially /discord edit and /discord reload).

Double-check that the config.yml is properly formatted and saved.

### 2. Discord Link Not Showing
   Make sure the link key in config.yml is correctly set with a valid Discord invite URL.

If the plugin doesn’t display the link, check the server console for any errors during startup.

