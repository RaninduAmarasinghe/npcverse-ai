# NPC-Verse AI 🤖✨

**NPC-Verse AI** is a specialized backend framework built with **Spring Boot** designed to bring non-player characters to life using local Large Language Models (LLMs). By leveraging **Ollama** and **Llama 3.2**, this project enables real-time, context-aware, and personality-driven conversations with NPCs without relying on external cloud APIs.

The project demonstrates how to bridge modern Java microservices with local AI orchestration, featuring characters with distinct backstories—ranging from medieval tavern owners like **Barnaby** to modern-day personalities based in **Colombo** like **Thisara**.

---

## 🚀 Key Features

* **Local AI Orchestration:** Fully integrated with **Ollama** to run models locally (optimized for Apple M1), ensuring privacy and zero latency from external providers.
* **Personality-Driven Dialogue:** Implements specialized system prompts for each NPC to maintain consistent tone, knowledge, and regional flair.
* **RESTful API:** A clean Spring Boot backend that handles prompt engineering and manages the communication flow between the user and the LLM.
* **Scalable Architecture:** Built to easily swap models (e.g., Llama 3.2, Mistral) or add new NPC profiles via MongoDB with minimal configuration changes.

---

## 🛠️ Tech Stack

* **Backend:** Java 21+, Spring Boot 3.x
* **AI Engine:** Ollama (Running Llama 3.2)
* **Database:** MongoDB (For conversation history and character profiles)
* **Build Tool:** Maven
* **Utilities:** Lombok, Spring WebFlux

---

## 📦 Getting Started

### Prerequisites

1.  **Ollama** installed and running on your local machine.
2.  Pull the **Llama 3.2** model:
    ```bash
    ollama pull llama3.2
    ```
3.  **JDK 21** or higher.
4.  **MongoDB** (Local instance or Atlas).

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/npc-verse-ai.git](https://github.com/your-username/npc-verse-ai.git)
    cd npc-verse-ai
    ```

2.  **Configure Environment:**
    Update `src/main/resources/application.properties` with your MongoDB URI and Ollama endpoint:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/npcverse
    ollama.api.url=http://localhost:11434/api/generate
    ```

3.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

---

## 🎭 Current Characters

| Name | Role | Location | Personality |
| :--- | :--- | :--- | :--- |
| **Barnaby** | Tavern Owner | Medieval Realm | Grumpy but informative, loves ale and rumors. |
| **Thisara** | Street Smart | Colombo, SL | Fast-talker, knows the city secrets, uses local slang. |

---

## 🛤️ Future Roadmap

- [ ] WebSocket integration for real-time streaming of AI responses.
- [ ] Voice-to-Text and Text-to-Voice support.
- [ ] "World State" memory where NPCs remember past player interactions.
- [ ] Frontend dashboard built with React/Next.js.

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.
