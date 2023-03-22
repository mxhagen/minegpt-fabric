# MineGPT 1.0

Use the OpenAI API within minecraft!
This is a client-sided mod, which adds the `/gpt` command to minecraft

---

### Overview

- [Preview](#preview) - What the mod looks like
- [Installation](#installation) - How you can install the mod
- [Usage](#usage) - How to use the mod

### Preview

![screenshot](./src/resources/assets/minegpt/preview.png)

### Installation

To use this mod, you will first need gain access to an [OpenAI API key](https://platform.openai.com/docs/api-reference).

**You can then simply download the current `.jar` file from the releases tab and place it in your mods folder.**

--- 

Alternatively, you can build the mod from source.

For this I will assume you are running any *POSIX compliant shell* like `sh`, `bash` or `zsh`.

When building from source and if you know what you are doing, you can even replace the default
value of `net.m_hgn.minegpt.api.Gpt3Api.API_KEY` to contain your API Key.
This eliminates the need to newly provide the key each session using the `api_key` subcommand.

To build the mod from source:

1. 
    Clone the repository
    ```sh
    git clone https://gitlab.com/m-hgn/minegpt-1.19.4.git
    ```
   
2.
   ***(Optional)*** Using your preferred text editor,
   you may change the initial value of `net.m_hgn.minegpt.api.Gpt3Api.API_KEY` to your api key.
   
   If you have configured the `EDITOR` environment variable, just use the following command.
   ```sh
   $EDITOR ./minegpt-1.19.4/src/main/java/net/m_hgn/minegpt/api/Gpt3Api.java
   ```
   
3.
    Build the `.jar` file.
    ```sh
    cd minegpt-1.19.4 && ./gradlew build
    ```

4.
    Install the mod.
    ```sh
    cp ./minegpt-1.19.4/build/libs/minegpt-1.0-fabric-1.19.4.jar ~/.minecraft/mods/
    ```

### Usage

Using the mod is really simple. You simply provide your OpenAi API Key using the `api_key` subcommand:
```
/gpt api_key <your key here>
```

Then you can interact with OpenAI's model `gpt-3.5-turbo` just by typing out your query:
```
/gpt How can I craft an armor stand?
```
