# Stickify

### 🛠️ Roadmap: Telegram Sticker WebApp (Flutter + Spring)

### 📦 Backend (Spring)

- `/generate` – генерация стикера по prompt (OpenAI / SDXL)
- `/upload` – приём и обработка изображения
- `/sticker?file_id=` – загрузка чужого стикера через Telegram API
- `/telegram/upload` – создать свой стикер / стикерпак

### 🎨 Frontend (Flutter WebApp)

- UI для:
    - Загрузки файла или `file_id`
    - Ввода prompt для генерации
    - Просмотра и редактирования (оверлей, текст, фильтры)
    - Отправки стикера в Telegram

### 🤖 Telegram

- WebApp открывается через бот
- Используется `getFile`, `createNewStickerSet`, `addStickerToSet`

---

### 📲 Пользовательский флоу:

1. 🔘 Загрузил стикер или prompt
2. 🎨 Отредактировал
3. 🚀 Отправил → получил свой стикер/пак
