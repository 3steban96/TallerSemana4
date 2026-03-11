const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = 3000;
const tasksFilePath = path.join(__dirname, 'tasks.json');

app.use(cors());
app.use(express.json());

// Helper function to read tasks
const readTasks = () => {
    try {
        const data = fs.readFileSync(tasksFilePath, 'utf8');
        return JSON.parse(data);
    } catch (error) {
        return [];
    }
};

// Helper function to write tasks
const writeTasks = (tasks) => {
    fs.writeFileSync(tasksFilePath, JSON.stringify(tasks, null, 2), 'utf8');
};

// GET: List all tasks (with 3-second delay for Selenium testing)
app.get('/api/tasks', (req, res) => {
    setTimeout(() => {
        const tasks = readTasks();
        res.json(tasks);
    }, 3000); // 3 seconds delay
});

// GET: Get a single task by ID
app.get('/api/tasks/:id', (req, res) => {
    const tasks = readTasks();
    const task = tasks.find(t => t.id === parseInt(req.params.id));
    if (!task) return res.status(404).json({ message: 'Task not found' });
    res.json(task);
});

// POST: Create a new task
app.post('/api/tasks', (req, res) => {
    const { title, description } = req.body;
    if (!title) return res.status(400).json({ message: 'Title is required' });

    const tasks = readTasks();
    const newTask = {
        id: tasks.length > 0 ? Math.max(...tasks.map(t => t.id)) + 1 : 1,
        title,
        description: description || '',
        completed: false
    };

    tasks.push(newTask);
    writeTasks(tasks);
    res.status(201).json(newTask);
});

// PUT: Update an existing task
app.put('/api/tasks/:id', (req, res) => {
    const { title, description, completed } = req.body;
    const tasks = readTasks();
    const index = tasks.findIndex(t => t.id === parseInt(req.params.id));

    if (index === -1) return res.status(404).json({ message: 'Task not found' });

    tasks[index] = {
        ...tasks[index],
        title: title !== undefined ? title : tasks[index].title,
        description: description !== undefined ? description : tasks[index].description,
        completed: completed !== undefined ? completed : tasks[index].completed
    };

    writeTasks(tasks);
    res.json(tasks[index]);
});

// DELETE: Remove a task
app.delete('/api/tasks/:id', (req, res) => {
    const tasks = readTasks();
    const index = tasks.findIndex(t => t.id === parseInt(req.params.id));

    if (index === -1) return res.status(404).json({ message: 'Task not found' });

    tasks.splice(index, 1);
    writeTasks(tasks);
    res.status(204).send();
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
