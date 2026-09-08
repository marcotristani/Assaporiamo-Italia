# 🍷 Assaporiamo l'Italia — Progetto Full‑Stack Enogastronomico

![Frontend React](https://img.shields.io/badge/Frontend-React-61DAFB?logo=react&logoColor=white&style=for-the-badge)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-grey?style=for-the-badge&logo=tailwind-css&logoColor=38B2AC)
![Backend Java](https://shields.io)
![Spring Boot](https://shields.io)
![Spring Security](https://shields.io)

Assaporiamo l'Italia è un'applicazione **full‑stack** concepita come un viaggio virtuale attraverso la cultura culinaria italiana. L'applicazione permette di effettuare un vero e proprio tour enogastronomico interattivo: partendo da una mappa o da una selezione regionale, l'utente può scoprire i prodotti tipici, le ricette tradizionali e i vini pregiati caratteristici di ogni specifica regione.

Questa repository è strutturata come una **monorepo** che organizza in modo pulito sia l'ecosistema di backend (Java/Spring) sia l'interfaccia utente di frontend (React/Tailwind).

---

## 🗺️ Funzionalità Principali

- **Tour Regionale Interattivo:** Navigazione guidata attraverso le 20 regioni d'Italia.
- **Catalogo Prodotti Tipici:** Schede dettagliate dei prodotti gastronomici tradizionali per ogni territorio.
- **Cantina Virtuale:** Selezione e abbinamento dei vini DOC e DOCG caratteristici di ciascuna regione.
- **Autenticazione Sicura:** Gestione degli accessi e dei ruoli utente per gestire i contenuti.

---

## 🛠️ Architettura e Tecnologie Utilizzate

### **Frontend**

- **React:** Per la creazione di un'interfaccia utente dinamica, modulare e reattiva.
- **Tailwind CSS:** Per un design moderno, utility-first e completamente responsive.
- **Axios / Fetch API:** Per la comunicazione asincrona con gli endpoint del backend.

### **Backend**

- **Java & Spring Boot:** Solida base per lo sviluppo dell'applicazione enterprise.
- **Maven:** Gestore delle dipendenze e build automation tool.
- **Spring Data JPA:** Per l'astrazione del livello di persistenza e l'interazione fluida con il database.
- **Spring Security:** Per l'implementazione di robusti protocolli di autenticazione e autorizzazione (es. JWT o sessioni sicure).

---

## 📂 Struttura della Repository

Il progetto è organizzato in due macro-aree indipendenti ma coordinate:

```text
Assaporiamo-Italia/
├── frontend/      # Applicazione React + Tailwind CSS
└── backend/       # Applicazione Java + Spring Boot (Maven)
```

---

## 🚀 Obiettivi del Progetto

Questo progetto nasce con l'obiettivo di consolidare competenze avanzate nell'architettura full-stack, unendo la flessibilità dell'ecosistema JavaScript/React sul client alla robustezza e sicurezza del mondo Java/Spring sul server.
