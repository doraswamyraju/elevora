import express, { Request, Response } from 'express';
import cors from 'cors';

const app = express();
const PORT = process.env.PORT || 5089;

app.use(cors());
app.use(express.json());

// Mock course content database extracted from Managerial English materials
const courseData = {
  courseName: "Managerial English Development Programme",
  duration: "6 months / 24 weeks",
  totalClasses: 72,
  modules: [
    {
      id: 1,
      number: 1,
      title: "Building Confidence & Core Workplace English",
      description: "Build the essential English skills you need for everyday workplace success.",
      totalWeeks: 4,
      totalLessons: 12,
      level: "Beginner",
      weeks: [
        {
          weekNumber: 1,
          title: "Getting Started & Everyday English",
          days: [
            {
              dayNumber: 1,
              title: "Introduction & Goals",
              overview: "Understand what you will learn in Module 1, know how the 4-week plan works, set personal goals, and learn key expressions.",
              videoDuration: "5:14",
              keyExpressions: [
                "I'm looking forward to learning.",
                "This course will help me at work.",
                "My goal is to improve my professional communication.",
                "I want to be more confident in meetings.",
                "I can practise a little every day."
              ],
              quickActivity: [
                { id: 1, before: "My goal is to ", after: " my communication skills.", options: ["improve", "moves", "change"], correct: "improve" },
                { id: 2, before: "I'm ", after: " forward to learning.", options: ["look", "looking", "looked"], correct: "looking" },
                { id: 3, before: "This course will ", after: " me at work.", options: ["help", "helps", "helping"], correct: "help" }
              ],
              quiz: [
                { id: 1, question: "What is the main goal of Module 1?", options: ["To improve professional English communication", "To learn advanced grammar only", "To prepare for a school exam", "To write long academic reports"], correct: 0 },
                { id: 2, question: "How many classes are planned per week?", options: ["1 class", "3 classes", "5 classes", "7 classes"], correct: 1 },
                { id: 3, question: "Which phrase is used to start your professional introduction?", options: ["My main responsibility is...", "I am leaving early today.", "Let us cancel the agenda.", "None of the above"], correct: 0 }
              ]
            },
            { dayNumber: 2, title: "Professional Introductions", isLocked: true },
            { dayNumber: 3, title: "Talking About Your Work", isLocked: true },
            { dayNumber: 4, title: "Workplace Vocabulary (Part 1)", isLocked: true },
            { dayNumber: 5, title: "Small Talk at Work", isLocked: true }
          ]
        },
        { weekNumber: 2, title: "Workplace Foundations", isLocked: true },
        { weekNumber: 3, title: "Meetings & Collaboration", isLocked: true },
        { weekNumber: 4, title: "Professional Writing", isLocked: true }
      ]
    },
    { id: 2, number: 2, title: "Workplace Conversation & Team Communication", totalLessons: 12 },
    { id: 3, number: 3, title: "Managing People, Meetings & Performance", totalLessons: 12 },
    { id: 4, number: 4, title: "Emails, Reports & Presentations", totalLessons: 12 },
    { id: 5, number: 5, title: "Difficult Conversations, Complaints & Problem Solving", totalLessons: 12 },
    { id: 6, number: 6, title: "Leadership, Fluency & Workplace Performance", totalLessons: 12 }
  ]
};

// Health Check API
app.get('/health', (req: Request, res: Response) => {
  res.json({
    status: 'ok',
    service: 'Elevora LMS Backend API',
    port: PORT,
    timestamp: new Date().toISOString()
  });
});

// Authentication endpoint (User & Admin/Trainer)
app.post('/api/auth/login', (req: Request, res: Response) => {
  const { email, password } = req.body;
  
  if (email === 'admin@elevora.com' && password === 'admin123') {
    return res.json({
      success: true,
      user: {
        id: 'admin_1',
        email: 'admin@elevora.com',
        name: 'Elevora Admin Trainer',
        role: 'TRAINER'
      },
      token: 'mock_trainer_jwt_token_elevora'
    });
  }

  if (email === 'user@elevora.com' && password === 'user123') {
    return res.json({
      success: true,
      user: {
        id: 'user_1',
        email: 'user@elevora.com',
        name: 'Priya Sharma',
        role: 'LEARNER'
      },
      token: 'mock_learner_jwt_token_elevora'
    });
  }

  return res.status(401).json({
    success: false,
    message: 'Invalid credentials. Please use user@elevora.com / user123 or admin@elevora.com / admin123'
  });
});

// Course API
app.get('/api/course/modules', (req: Request, res: Response) => {
  res.json({
    success: true,
    data: courseData.modules
  });
});

app.get('/api/course/module/:moduleId/week/:weekId/day/:dayId', (req: Request, res: Response) => {
  const { moduleId, weekId, dayId } = req.params;
  const mod = courseData.modules.find(m => m.id === parseInt(moduleId));
  const week = mod?.weeks?.find(w => w.weekNumber === parseInt(weekId));
  const day = week?.days?.find(d => d.dayNumber === parseInt(dayId));

  if (!day) {
    return res.status(404).json({ success: false, message: 'Lesson day not found' });
  }

  return res.json({ success: true, data: day });
});

// Trainer specific API
app.get('/api/trainer/overview', (req: Request, res: Response) => {
  res.json({
    success: true,
    trainer: 'admin@elevora.com',
    stats: {
      activeStudents: 28,
      completedAssessments: 142,
      averageFluencyScore: '4.2/5.0',
      currentModule: 'Module 1: Building Confidence & Core Workplace English'
    }
  });
});

app.listen(PORT, () => {
  console.log(`Elevora Backend API running on port ${PORT}`);
});

