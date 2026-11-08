import express, { Request, Response } from 'express';
import cors from 'cors';
import { GoogleGenAI } from '@google/genai';

const app = express();
const PORT = process.env.PORT || 5089;

app.use(cors());
app.use(express.json());

// Curriculum definitions for Managerial English Course
export const modulesMasterList = [
  {
    id: 1,
    number: 1,
    title: "Building Confidence & Core Workplace English",
    description: "Build essential English skills for everyday workplace success: professional introductions, job roles, daily schedules, and clear questions.",
    weeksCount: 4,
    lessonsCount: 12,
    level: "Beginner to Intermediate",
    weeks: [
      {
        weekNumber: 1,
        title: "Professional Introductions & Workplace Routine",
        days: [
          { dayNumber: 1, title: "Professional Introductions", hasFullModule: true },
          { dayNumber: 2, title: "Job Roles & Responsibilities", hasFullModule: false },
          { dayNumber: 3, title: "Present Simple for Work", hasFullModule: false }
        ]
      },
      {
        weekNumber: 2,
        title: "Past & Future Workplace Communication",
        days: [
          { dayNumber: 4, title: "Past Experiences", hasFullModule: false },
          { dayNumber: 5, title: "Future Plans & Commitments", hasFullModule: false },
          { dayNumber: 6, title: "Questions & Clarification", hasFullModule: false }
        ]
      },
      {
        weekNumber: 3,
        title: "Workplace Foundations & Describing Situations",
        days: [
          { dayNumber: 7, title: "Workplace Vocabulary", hasFullModule: false },
          { dayNumber: 8, title: "Describing People & Situations", hasFullModule: false },
          { dayNumber: 9, title: "Time, Schedules & Routines", hasFullModule: false }
        ]
      },
      {
        weekNumber: 4,
        title: "Grammar Accuracy & Conversational Fluency",
        days: [
          { dayNumber: 10, title: "Common Grammar Errors", hasFullModule: false },
          { dayNumber: 11, title: "Confidence-Building Conversations", hasFullModule: false },
          { dayNumber: 12, title: "Module 1 Review & Practical Assessment", hasFullModule: false }
        ]
      }
    ]
  },
  {
    id: 2,
    number: 2,
    title: "Workplace Conversation & Team Communication",
    description: "Starting conversations, asking and giving information, clarifying, telephone calls, giving instructions, and delegating.",
    weeksCount: 4,
    lessonsCount: 12,
    level: "Intermediate",
    weeks: [
      { weekNumber: 5, title: "Casual & Professional Dialogue", days: [{ dayNumber: 13, title: "Starting Conversations" }, { dayNumber: 14, title: "Small Talk at Work" }, { dayNumber: 15, title: "Asking for Information" }] },
      { weekNumber: 6, title: "Information Exchange & Calls", days: [{ dayNumber: 16, title: "Giving Information" }, { dayNumber: 17, title: "Clarifying & Checking Understanding" }, { dayNumber: 18, title: "Telephone & Video Calls" }] },
      { weekNumber: 7, title: "Instructions & Task Delegation", days: [{ dayNumber: 19, title: "Professional Messaging" }, { dayNumber: 20, title: "Giving Instructions" }, { dayNumber: 21, title: "Delegating Tasks" }] },
      { weekNumber: 8, title: "Polite Requests & Review", days: [{ dayNumber: 22, title: "Following Up" }, { dayNumber: 23, title: "Making Requests Politely" }, { dayNumber: 24, title: "Module 2 Review" }] }
    ]
  },
  {
    id: 3,
    number: 3,
    title: "Managing People, Meetings & Performance",
    description: "Setting agendas, managing turn-taking, giving updates, positive and corrective feedback, performance discussions, and coaching.",
    weeksCount: 4,
    lessonsCount: 12,
    level: "Intermediate",
    weeks: [
      { weekNumber: 9, title: "Meeting Structure & Agendas", days: [{ dayNumber: 25, title: "Opening a Meeting" }, { dayNumber: 26, title: "Setting Agendas" }, { dayNumber: 27, title: "Managing Turn-Taking" }] },
      { weekNumber: 10, title: "Updates & Accountability", days: [{ dayNumber: 28, title: "Giving Updates" }, { dayNumber: 29, title: "Giving Instructions Clearly" }, { dayNumber: 30, title: "Delegation & Accountability" }] },
      { weekNumber: 11, title: "Feedback & Performance", days: [{ dayNumber: 31, title: "Positive Feedback" }, { dayNumber: 32, title: "Corrective Feedback" }, { dayNumber: 33, title: "Performance Discussions" }] },
      { weekNumber: 12, title: "Targets & Coaching", days: [{ dayNumber: 34, title: "Targets & Deadlines" }, { dayNumber: 35, title: "Coaching Conversations" }, { dayNumber: 36, title: "Module 3 Review" }] }
    ]
  },
  {
    id: 4,
    number: 4,
    title: "Emails, Reports & Presentations",
    description: "Email structure, professional subject lines, apologies, executive summaries, data explanations, and presentation delivery.",
    weeksCount: 4,
    lessonsCount: 12,
    level: "Upper-Intermediate",
    weeks: [
      { weekNumber: 13, title: "Email Essentials", days: [{ dayNumber: 37, title: "Email Structure" }, { dayNumber: 38, title: "Professional Subject Lines" }, { dayNumber: 39, title: "Requests & Follow-ups" }] },
      { weekNumber: 14, title: "Executive Summaries & Reports", days: [{ dayNumber: 40, title: "Apologies & Difficult Emails" }, { dayNumber: 41, title: "Reports & Summaries" }, { dayNumber: 42, title: "Describing Results" }] },
      { weekNumber: 15, title: "Data & Presentation Openings", days: [{ dayNumber: 43, title: "Explaining Data" }, { dayNumber: 44, title: "Presentation Openings" }, { dayNumber: 45, title: "Signposting Ideas" }] },
      { weekNumber: 16, title: "Q&A & Presentations Closing", days: [{ dayNumber: 46, title: "Handling Questions" }, { dayNumber: 47, title: "Closing Presentations" }, { dayNumber: 48, title: "Module 4 Review" }] }
    ]
  },
  {
    id: 5,
    number: 5,
    title: "Difficult Conversations, Complaints & Problem Solving",
    description: "Active listening, customer complaints, conflict management, polite disagreement, negotiation, and executive escalation.",
    weeksCount: 4,
    lessonsCount: 12,
    level: "Advanced",
    weeks: [
      { weekNumber: 17, title: "Active Listening & Customer Care", days: [{ dayNumber: 49, title: "Active Listening" }, { dayNumber: 50, title: "Customer Complaints" }, { dayNumber: 51, title: "Complaint Resolution" }] },
      { weekNumber: 18, title: "Conflict Resolution & Disagreement", days: [{ dayNumber: 52, title: "Employee Concerns" }, { dayNumber: 53, title: "Conflict Management" }, { dayNumber: 54, title: "Polite Disagreement" }] },
      { weekNumber: 19, title: "Negotiation & Persuasion", days: [{ dayNumber: 55, title: "Negotiation" }, { dayNumber: 56, title: "Persuasion" }, { dayNumber: 57, title: "Saying No Professionally" }] },
      { weekNumber: 20, title: "Problem Solving & Escalation", days: [{ dayNumber: 58, title: "Problem-Solving Language" }, { dayNumber: 59, title: "Escalation & Solutions" }, { dayNumber: 60, title: "Module 5 Review" }] }
    ]
  },
  {
    id: 6,
    number: 6,
    title: "Leadership, Fluency & Workplace Performance",
    description: "Inspiring teams, delegating with authority, executive updates, interview techniques, and management simulation.",
    weeksCount: 4,
    lessonsCount: 12,
    level: "Mastery",
    weeks: [
      { weekNumber: 21, title: "Inspiring & Motivating", days: [{ dayNumber: 61, title: "Leadership Language" }, { dayNumber: 62, title: "Motivating Employees" }, { dayNumber: 63, title: "Delegating with Confidence" }] },
      { weekNumber: 22, title: "Meetings & Decision Making", days: [{ dayNumber: 64, title: "Running a Full Meeting" }, { dayNumber: 65, title: "Decision-Making Language" }, { dayNumber: 66, title: "Explaining Priorities" }] },
      { weekNumber: 23, title: "Executive Pressure & Hiring", days: [{ dayNumber: 67, title: "Handling Pressure" }, { dayNumber: 68, title: "Executive-Style Updates" }, { dayNumber: 69, title: "Interviewing & Hiring" }] },
      { weekNumber: 24, title: "Final Management Simulation", days: [{ dayNumber: 70, title: "Leadership Presentation" }, { dayNumber: 71, title: "Integrated Management Simulation" }, { dayNumber: 72, title: "Final Assessment & Action Plan" }] }
    ]
  }
];

// Exact Day 1 Content from Learner Book & Trainer Guide
export const day1ExactContent = {
  moduleNumber: 1,
  moduleTitle: "Building Confidence & Core Workplace English",
  weekNumber: 1,
  classNumber: 1,
  lessonTitle: "Professional Introductions",
  objectives: [
    "Use English confidently for professional introductions.",
    "Apply the target language in a realistic managerial situation.",
    "Speak clearly for 2–5 minutes with reduced hesitation."
  ],
  targetVocabulary: [
    { word: "role", definition: "The position or purpose that someone has in an organization", example: "My role involves coordinating international deliveries." },
    { word: "department", definition: "A division of a large organization dealing with a specific area", example: "I lead the Operations department at our regional hub." },
    { word: "experience", definition: "Knowledge or skill gained from doing a job over time", example: "I have over eight years of experience in supply chain." },
    { word: "responsibility", definition: "A duty or obligation to deal with something or of having control", example: "My main responsibility is ensuring on-time fulfillment." },
    { word: "position", definition: "A job or post within an organization", example: "She accepted the Senior Product Lead position." },
    { word: "team", definition: "A group of people who collaborate to achieve a common goal", example: "Our team consists of twelve skilled analysts." },
    { word: "background", definition: "A person's education, experience, and past work history", example: "My background is primarily in industrial engineering." },
    { word: "objective", definition: "A specific goal or aim that you want to achieve", example: "Our primary objective this quarter is increasing retention." }
  ],
  languageFocus: "Use short, clear sentences; choose the correct tense; ask follow-up questions; avoid translating word-for-word from your native language.",
  sayItPhrases: [
    "Let me explain that clearly.",
    "Could you please clarify that?",
    "My main responsibility is…",
    "The priority is…",
    "Let’s look at the best solution.",
    "I’d like to make sure we agree on the next step."
  ],
  controlledPractice: [
    { type: "sentence_formation", instruction: "Make a complete sentence with 5 target vocabulary words." },
    { type: "statement_to_question", instruction: "Transform 3 affirmative statements into managerial questions." },
    { type: "workplace_example", instruction: "Share one real-world introduction from your actual department." }
  ],
  rolePlayScenario: {
    title: "Manager and Employee Scenario: Professional Introduction",
    context: "You are meeting a new department lead or cross-functional team member for the first time.",
    managerInstructions: "Explain your role clearly, ask at least three follow-up questions about their department, and agree on a clear next step.",
    sampleDialogue: [
      { speaker: "Manager", text: "Good morning! My name is Priya, and I oversee the Operations department." },
      { speaker: "Colleague", text: "Good morning Priya, great to meet you! I recently joined the Logistics team." },
      { speaker: "Manager", text: "Welcome to the team! Could you please clarify your main responsibilities for this quarter?" }
    ]
  },
  reflection: {
    prompt1: "One phrase I will use this week:",
    prompt2: "One mistake I want to avoid:",
    confidenceRating: "Confidence today (1–5 scale)"
  },
  homework: "Prepare a 2-minute spoken response introducing yourself professionally, and write 5 sentences using the new vocabulary."
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

// Auth endpoint
app.post('/api/auth/login', (req: Request, res: Response) => {
  const { email, password } = req.body;
  if (email === 'admin@elevora.com' && password === 'admin123') {
    return res.json({
      success: true,
      user: { id: 'admin_1', email: 'admin@elevora.com', name: 'Elevora Admin Trainer', role: 'TRAINER' },
      token: 'mock_trainer_jwt_token_elevora'
    });
  }
  if (email === 'user@elevora.com' && password === 'user123') {
    return res.json({
      success: true,
      user: { id: 'user_1', email: 'user@elevora.com', name: 'Priya Sharma', role: 'LEARNER' },
      token: 'mock_learner_jwt_token_elevora'
    });
  }
  return res.status(401).json({
    success: false,
    message: 'Invalid credentials. Use user@elevora.com / user123 or admin@elevora.com / admin123'
  });
});

// Curriculum endpoints
app.get('/api/course/modules', (req: Request, res: Response) => {
  res.json({ success: true, data: modulesMasterList });
});

app.get('/api/course/day1', (req: Request, res: Response) => {
  res.json({ success: true, data: day1ExactContent });
});

// AI Practice Partner Endpoint with Token Telemetry
app.post('/api/ai/practice-feedback', async (req: Request, res: Response) => {
  try {
    const { userSpokenText, apiKey, scenarioContext } = req.body;
    const keyToUse = apiKey || process.env.GEMINI_API_KEY;

    if (!userSpokenText || userSpokenText.trim() === '') {
      return res.status(400).json({ success: false, message: 'Please provide user spoken text.' });
    }

    if (!keyToUse) {
      // Mock fallback with realistic metrics if API key not entered yet
      return res.json({
        success: true,
        isSimulated: true,
        rolePlayReply: "Nice to meet you! As a department lead, it's great to hear your background. Could you clarify what your main priority is for this week?",
        feedback: {
          fluencyScore: 4,
          accuracyScore: 4.5,
          improvedAlternative: "Good morning! My name is Priya. I have eight years of experience, and my main responsibility is overseeing the Operations department.",
          vocabularyUsed: ["role", "experience", "department", "responsibility"],
          vocabularyMissing: ["position", "team", "background", "objective"],
          trainerObservation: "Strong professional opening. Good pace with minimal hesitation."
        },
        telemetry: {
          promptTokens: 142,
          completionTokens: 88,
          totalTokens: 230,
          estimatedCostUsd: 0.000035,
          model: "gemini-2.5-flash"
        }
      });
    }

    // Call Google GenAI SDK
    const ai = new GoogleGenAI({ apiKey: keyToUse });
    const prompt = `
You are a senior executive communication coach for the Elevora Managerial English Programme.
Learner's current scenario: Module 1, Lesson 1 — Professional Introductions.
Target vocabulary words: [role, department, experience, responsibility, position, team, background, objective].
Target phrases: ["Let me explain that clearly.", "Could you please clarify that?", "My main responsibility is…", "The priority is…", "Let's look at the best solution.", "I'd like to make sure we agree on the next step."].

The learner said: "${userSpokenText}"

Respond with a strictly formatted JSON object:
{
  "rolePlayReply": "A realistic, professional managerial dialogue response as their conversation partner (max 2 sentences)",
  "fluencyScore": 4, // 1 to 5 scale
  "accuracyScore": 4, // 1 to 5 scale
  "improvedAlternative": "A more polished, executive phrasing of what the learner tried to say",
  "vocabularyUsed": ["array of target words they used"],
  "vocabularyMissing": ["array of target words they could have used"],
  "trainerObservation": "One concise sentence of constructive actionable feedback"
}
`;

    const response = await ai.models.generateContent({
      model: 'gemini-2.5-flash',
      contents: prompt,
      config: {
        responseMimeType: 'application/json'
      }
    });

    const responseText = response.text || '{}';
    const parsedData = JSON.parse(responseText);

    const usage = response.usageMetadata || { promptTokenCount: 150, candidatesTokenCount: 90, totalTokenCount: 240 };
    const promptTokens = usage.promptTokenCount || 150;
    const completionTokens = usage.candidatesTokenCount || 90;
    const totalTokens = usage.totalTokenCount || (promptTokens + completionTokens);

    return res.json({
      success: true,
      isSimulated: false,
      rolePlayReply: parsedData.rolePlayReply || "Great to meet you. Let's look at the best solution together.",
      feedback: {
        fluencyScore: parsedData.fluencyScore || 4,
        accuracyScore: parsedData.accuracyScore || 4,
        improvedAlternative: parsedData.improvedAlternative || userSpokenText,
        vocabularyUsed: parsedData.vocabularyUsed || [],
        vocabularyMissing: parsedData.vocabularyMissing || [],
        trainerObservation: parsedData.trainerObservation || "Good workplace tone."
      },
      telemetry: {
        promptTokens,
        completionTokens,
        totalTokens,
        estimatedCostUsd: Number(((totalTokens / 1000000) * 0.15).toFixed(6)),
        model: "gemini-2.5-flash"
      }
    });

  } catch (err: any) {
    console.error("AI Evaluation error:", err);
    return res.status(500).json({
      success: false,
      message: err.message || "Failed to generate AI evaluation",
      error: String(err)
    });
  }
});

app.listen(PORT, () => {
  console.log(`Elevora Backend API running on port ${PORT}`);
});
