import React, { useState } from 'react';
import { 
  BookOpen, 
  CheckCircle, 
  Lock, 
  Play, 
  Volume2, 
  Mic, 
  HelpCircle, 
  Award, 
  ChevronRight, 
  ArrowLeft,
  User,
  Shield,
  LogOut,
  Sparkles,
  Zap,
  Activity,
  MessageSquare,
  RefreshCw,
  Send,
  Home
} from 'lucide-react';
import { LandingPage } from './components/landing/LandingPage';

export function App() {
  // Navigation mode: 'landing' | 'app' | 'login'
  const [appMode, setAppMode] = useState<'landing' | 'app' | 'login'>('landing');

  const [user, setUser] = useState<{ email: string; role: 'LEARNER' | 'TRAINER'; name: string } | null>({
    email: 'user@elevora.com',
    role: 'LEARNER',
    name: 'Priya Sharma'
  });
  const [emailInput, setEmailInput] = useState('user@elevora.com');
  const [passwordInput, setPasswordInput] = useState('user123');
  const [authError, setAuthError] = useState('');

  // Course Navigation Views:
  // 'module-overview' | 'week-plan' | 'day-hub' | 'step-video' | 'step-words' | 'step-language' | 'step-controlled' | 'step-roleplay' | 'step-quiz' | 'step-completed'
  const [view, setView] = useState<string>('module-overview');

  // Role-play practice mode: 'without-ai' | 'with-ai'
  const [rolePlayMode, setRolePlayMode] = useState<'without-ai' | 'with-ai'>('without-ai');
  const [spokenText, setSpokenText] = useState("Good morning! My name is Priya, and I lead the Operations department. My main responsibility is overseeing cross-functional deliveries.");
  const [aiApiKey, setAiApiKey] = useState("");
  const [isAiLoading, setIsAiLoading] = useState(false);
  const [aiFeedback, setAiFeedback] = useState<{
    reply: string;
    fluency: number;
    improved: string;
    tokens: number;
    cost: number;
  } | null>(null);
  const [aiError, setAiError] = useState<string | null>(null);

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (emailInput === 'admin@elevora.com' && passwordInput === 'admin123') {
      setUser({ email: 'admin@elevora.com', role: 'TRAINER', name: 'Elevora Admin Trainer' });
      setAuthError('');
      setAppMode('app');
    } else if (emailInput === 'user@elevora.com' && passwordInput === 'user123') {
      setUser({ email: 'user@elevora.com', role: 'LEARNER', name: 'Priya Sharma' });
      setAuthError('');
      setAppMode('app');
    } else {
      setAuthError('Invalid credentials. Use user@elevora.com / user123 or admin@elevora.com / admin123');
    }
  };

  const handleSendToAi = async () => {
    setIsAiLoading(true);
    setAiError(null);
    try {
      const res = await fetch('/api/ai/practice-feedback', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          userSpokenText: spokenText,
          apiKey: aiApiKey || undefined
        })
      });
      const data = await res.json();
      if (data.success && !data.isSimulated) {
        setAiFeedback({
          reply: data.rolePlayReply,
          fluency: data.feedback?.fluencyScore || 0,
          improved: data.feedback?.improvedAlternative || '',
          tokens: data.telemetry?.totalTokens || 0,
          cost: data.telemetry?.estimatedCostUsd || 0
        });
      } else {
        setAiFeedback(null);
        setAiError(data.message || 'Not Connected: AI Coach service is unavailable.');
      }
    } catch {
      setAiFeedback(null);
      setAiError('Not Connected: Live AI Coach is unreachable. Please verify internet connection.');
    } finally {
      setIsAiLoading(false);
    }
  };

  // If in Landing Page mode, render the rich landing page
  if (appMode === 'landing') {
    return (
      <LandingPage 
        onEnterApp={() => setAppMode('app')} 
        onLoginClick={() => setAppMode('login')} 
      />
    );
  }

  // If user requested Login or is not signed in
  if (appMode === 'login' || !user) {
    return (
      <div className="min-h-screen bg-[#080C16] text-white flex items-center justify-center p-4 relative">
        <button 
          onClick={() => setAppMode('landing')}
          className="absolute top-6 left-6 flex items-center gap-2 text-xs font-semibold text-slate-400 hover:text-amber-400 bg-slate-900/60 border border-slate-800 px-3.5 py-2 rounded-xl transition"
        >
          <ArrowLeft className="w-4 h-4" />
          <span>Back to Landing Page</span>
        </button>

        <div className="w-full max-w-md bg-[#131C31] border border-[#24304D] rounded-3xl p-8 shadow-2xl relative overflow-hidden">
          <div className="absolute top-0 right-0 w-32 h-32 bg-[#F39C12]/10 blur-3xl pointer-events-none rounded-full" />
          <div className="flex flex-col items-center mb-6">
            <div className="w-16 h-16 rounded-2xl bg-[#0B132B] border border-[#F39C12] flex items-center justify-center font-bold text-3xl text-[#F39C12] mb-3 shadow-[0_0_20px_rgba(243,156,18,0.25)]">
              E
            </div>
            <h1 className="text-2xl font-bold tracking-tight">Elevora</h1>
            <p className="text-xs text-gray-400 mt-1">Managerial English Development Programme</p>
          </div>

          {authError && (
            <div className="mb-4 p-3 bg-red-950/60 border border-red-700/80 rounded-xl text-xs text-red-200">
              {authError}
            </div>
          )}

          <form onSubmit={handleLogin} className="space-y-4">
            <div>
              <label className="block text-xs font-semibold text-gray-300 mb-1">Corporate Email</label>
              <input
                type="email"
                value={emailInput}
                onChange={e => setEmailInput(e.target.value)}
                className="w-full bg-[#080C16] border border-[#24304D] rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none focus:border-[#F39C12] transition"
                required
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-gray-300 mb-1">Password</label>
              <input
                type="password"
                value={passwordInput}
                onChange={e => setPasswordInput(e.target.value)}
                className="w-full bg-[#080C16] border border-[#24304D] rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none focus:border-[#F39C12] transition"
                required
              />
            </div>
            <button
              type="submit"
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)] active:scale-95 text-sm"
            >
              Sign In to Programme
            </button>
          </form>

          <div className="mt-6 pt-4 border-t border-[#24304D] text-xs text-gray-400 space-y-1">
            <p className="font-semibold text-gray-300">Credentials:</p>
            <p>• Learner: <code className="text-amber-400">user@elevora.com</code> / <code className="text-amber-400">user123</code></p>
            <p>• Trainer: <code className="text-amber-400">admin@elevora.com</code> / <code className="text-amber-400">admin123</code></p>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-[#080C16] text-white flex flex-col font-sans">
      {/* Top Navbar */}
      <header className="h-16 border-b border-[#24304D] bg-[#0B132B]/90 backdrop-blur-md px-6 flex items-center justify-between sticky top-0 z-50">
        <div className="flex items-center gap-3">
          <img 
            src="/assets/elevora-logo-banner.png" 
            alt="Elevora" 
            className="h-8 w-auto object-contain"
          />
        </div>

        <div className="flex items-center gap-4">
          <button
            onClick={() => setAppMode('landing')}
            className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl bg-slate-900 border border-slate-700/80 text-xs font-semibold text-slate-300 hover:text-amber-400 transition"
          >
            <Home className="w-3.5 h-3.5" />
            <span>Website</span>
          </button>

          <div className="flex items-center gap-2 bg-[#131C31] border border-[#24304D] rounded-full px-3.5 py-1.5 shadow-sm">
            {user.role === 'TRAINER' ? <Shield className="w-4 h-4 text-[#F39C12]" /> : <User className="w-4 h-4 text-[#F39C12]" />}
            <span className="text-xs font-semibold">{user.name}</span>
            <span className="text-[10px] bg-[#F39C12]/20 text-[#F39C12] font-bold px-2 py-0.5 rounded-full">
              {user.role}
            </span>
          </div>

          <button
            onClick={() => {
              setUser(null);
              setAppMode('landing');
            }}
            className="p-2 rounded-full bg-[#131C31] hover:bg-amber-950/40 border border-[#24304D] text-gray-400 hover:text-[#F39C12] transition"
            title="Log Out"
          >
            <LogOut className="w-4 h-4" />
          </button>
        </div>
      </header>

      {/* Main App Container */}
      <main className="flex-1 max-w-4xl w-full mx-auto p-4 md:p-6">

        {/* 1. MODULE OVERVIEW */}
        {view === 'module-overview' && (
          <div className="space-y-6 animate-fade-in">
            {/* Visual Boardroom Banner */}
            <div className="relative rounded-3xl overflow-hidden border border-[#24304D] shadow-2xl group">
              <img 
                src="/module1_banner.jpg" 
                alt="Executive Boardroom" 
                className="w-full h-56 object-cover object-center filter brightness-90 group-hover:scale-105 transition duration-700" 
              />
              <div className="absolute inset-0 bg-gradient-to-t from-[#080C16] via-[#080C16]/60 to-transparent p-6 flex flex-col justify-end">
                <span className="text-xs font-bold text-[#F39C12] uppercase tracking-widest">Module 1 • Flagship</span>
                <h2 className="text-2xl font-bold text-white mt-1">
                  Building Confidence & Core Workplace English
                </h2>
                <p className="text-xs text-gray-300 mt-1 max-w-xl">
                  Build essential English skills for everyday workplace success: professional introductions, job roles, daily schedules, and assertive questions.
                </p>
                <div className="flex items-center gap-3 mt-4 text-xs">
                  <span className="bg-[#131C31]/80 backdrop-blur border border-[#24304D] px-3 py-1 rounded-full text-amber-300">📅 4 Weeks</span>
                  <span className="bg-[#131C31]/80 backdrop-blur border border-[#24304D] px-3 py-1 rounded-full text-amber-300">📖 12 Classes</span>
                  <span className="bg-[#131C31]/80 backdrop-blur border border-[#24304D] px-3 py-1 rounded-full text-amber-300">🎯 Managerial Level</span>
                </div>
              </div>
            </div>

            {/* Weeks in this Module */}
            <div>
              <h3 className="text-xs font-bold text-gray-400 uppercase tracking-widest mb-3">Weeks in Module 1</h3>
              <div className="space-y-3">
                <div
                  onClick={() => setView('week-plan')}
                  className="bg-[#131C31] hover:bg-[#18233C] border border-[#F39C12] rounded-2xl p-5 flex items-center justify-between cursor-pointer transition shadow-[0_0_15px_rgba(243,156,18,0.15)] group"
                >
                  <div className="flex items-center gap-4">
                    <div className="w-10 h-10 rounded-xl bg-[#0B132B] border border-[#F39C12] flex items-center justify-center font-bold text-sm text-[#F39C12]">
                      W1
                    </div>
                    <div>
                      <span className="text-xs font-bold text-[#F39C12]">Week 1</span>
                      <h4 className="text-sm font-bold text-white">Professional Introductions & Workplace Routine</h4>
                      <p className="text-[11px] text-gray-400">Class 1: Introductions • Class 2: Job Roles • Class 3: Present Simple</p>
                    </div>
                  </div>
                  <ChevronRight className="w-5 h-5 text-[#F39C12] group-hover:translate-x-1 transition" />
                </div>

                {[
                  { w: 2, title: "Past & Future Workplace Communication", sub: "Class 4: Past Experiences • Class 5: Future Plans • Class 6: Questions & Clarification" },
                  { w: 3, title: "Workplace Foundations & Describing Situations", sub: "Class 7: Workplace Vocab • Class 8: Describing People • Class 9: Time & Routines" },
                  { w: 4, title: "Grammar Accuracy & Conversational Fluency", sub: "Class 10: Common Errors • Class 11: Confidence Conversations • Class 12: Review" }
                ].map(wk => (
                  <div key={wk.w} className="bg-[#050810] border border-[#24304D] rounded-2xl p-4 flex items-center justify-between opacity-60">
                    <div className="flex items-center gap-4">
                      <div className="w-10 h-10 rounded-xl bg-[#131C31] border border-[#24304D] flex items-center justify-center text-gray-500 font-bold text-xs">
                        <Lock className="w-4 h-4" />
                      </div>
                      <div>
                        <span className="text-xs font-medium text-gray-400">Week {wk.w}</span>
                        <h4 className="text-sm font-semibold text-gray-300">{wk.title}</h4>
                        <p className="text-[11px] text-gray-500">{wk.sub}</p>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        )}

        {/* 2. WEEK 1 DAYS PLAN */}
        {view === 'week-plan' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('module-overview')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white transition"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Module 1 Overview
            </button>

            <div>
              <span className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Week 1 Curriculum</span>
              <h2 className="text-xl font-bold">Professional Introductions & Workplace Routine</h2>
              <p className="text-xs text-gray-400 mt-1">Weekly routine: Class 1 = Learn | Class 2 = Practise | Class 3 = Apply</p>
            </div>

            <div className="space-y-3">
              <div
                onClick={() => setView('day-hub')}
                className="bg-[#131C31] hover:bg-[#18233C] border border-[#F39C12] rounded-2xl p-5 flex items-center justify-between cursor-pointer transition shadow-[0_0_15px_rgba(243,156,18,0.15)] group"
              >
                <div className="flex items-center gap-4">
                  <div className="w-11 h-11 rounded-full bg-[#F39C12]/20 border border-[#F39C12] flex items-center justify-center text-[#F39C12]">
                    <Play className="w-5 h-5 fill-current ml-0.5" />
                  </div>
                  <div>
                    <span className="text-xs font-bold text-[#F39C12]">Class 1 (Day 1) • Full Lesson Plan Active</span>
                    <h4 className="text-base font-bold text-white">Professional Introductions</h4>
                    <p className="text-xs text-gray-400">8 Target Words, Language Rules, 6 Key Phrases & Role-Play</p>
                  </div>
                </div>
                <div className="w-9 h-9 rounded-full border-2 border-[#F39C12] flex items-center justify-center text-[11px] font-bold text-[#F39C12]">
                  0%
                </div>
              </div>

              {[
                { day: 2, title: "Job Roles & Responsibilities", sub: "Manage, supervise, coordinate, oversee & reporting structures" },
                { day: 3, title: "Present Simple for Work", sub: "Daily managerial routines, schedules, monitoring & reviews" }
              ].map(d => (
                <div key={d.day} className="bg-[#050810] border border-[#24304D] rounded-2xl p-4 flex items-center justify-between opacity-60">
                  <div className="flex items-center gap-4">
                    <div className="w-10 h-10 rounded-full bg-[#131C31] border border-[#24304D] flex items-center justify-center text-gray-500">
                      <Lock className="w-4 h-4" />
                    </div>
                    <div>
                      <span className="text-xs font-medium text-gray-400">Class {d.day} (Day {d.day})</span>
                      <h4 className="text-sm font-semibold text-gray-300">{d.title}</h4>
                      <p className="text-[11px] text-gray-500">{d.sub}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* 3. DAY 1 HUB (7 STEP ROUTINE) */}
        {view === 'day-hub' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('week-plan')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white transition"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Week 1
            </button>

            {/* Trainer Banner */}
            <div className="relative rounded-2xl overflow-hidden border border-[#24304D] h-40">
              <img src="/trainer_priya_banner.jpg" alt="Executive Coach" className="w-full h-full object-cover" />
              <div className="absolute inset-0 bg-gradient-to-t from-[#080C16] via-[#080C16]/60 to-transparent p-5 flex flex-col justify-end">
                <span className="text-xs font-bold text-[#F39C12]">Lesson 1 Delivery Framework</span>
                <h3 className="text-xl font-bold">Professional Introductions</h3>
                <p className="text-xs text-gray-300">Target: Speak clearly for 2–5 minutes with reduced hesitation.</p>
              </div>
            </div>

            <div className="space-y-3">
              {[
                { id: 'step-video', num: '01', title: 'Lesson Video & Objectives', sub: 'Understand goals and 5-min executive warm-up', time: '5 min', current: true },
                { id: 'step-words', num: '02', title: 'Words for Work', sub: '8 target managerial vocabulary words with definitions & examples', time: '10 min' },
                { id: 'step-language', num: '03', title: 'Language Focus & Say It', sub: 'Sentence rules and 6 key phrases for executive meetings', time: '10 min' },
                { id: 'step-controlled', num: '04', title: 'Controlled Practice', sub: 'Fill in the blanks & statement-to-question transformations', time: '10 min' },
                { id: 'step-roleplay', num: '05', title: 'Practice Area (With AI & Without AI)', sub: 'Manager role-play drill vs live Google Gemini AI conversation partner', time: '15 min', highlight: true },
                { id: 'step-quiz', num: '06', title: 'Check Your Understanding (Quiz)', sub: '5 comprehension questions on professional introductions', time: '5 min' },
                { id: 'step-completed', num: '07', title: 'Lesson Reflection & Self-Audit', sub: 'Personal goal, error audit, confidence rating (1–5)', time: '5 min' }
              ].map(step => (
                <div
                  key={step.id}
                  onClick={() => setView(step.id)}
                  className={`border rounded-2xl p-4 flex items-center justify-between cursor-pointer transition ${
                    step.highlight 
                      ? 'bg-[#18233C] border-[#F39C12] shadow-[0_0_15px_rgba(243,156,18,0.2)]'
                      : 'bg-[#131C31] hover:bg-[#18233C] border-[#24304D] hover:border-[#F39C12]'
                  }`}
                >
                  <div className="flex items-center gap-4">
                    <span className="w-8 h-8 rounded-lg bg-[#0B132B] border border-[#F39C12] text-[#F39C12] font-bold text-xs flex items-center justify-center">
                      {step.num}
                    </span>
                    <div>
                      <div className="flex items-center gap-2">
                        <h4 className="text-sm font-bold text-white">{step.title}</h4>
                        <span className="text-[10px] text-gray-400 bg-[#080C16] px-2 py-0.5 rounded border border-[#24304D]">{step.time}</span>
                      </div>
                      <p className="text-xs text-gray-400 mt-0.5">{step.sub}</p>
                    </div>
                  </div>
                  <ChevronRight className="w-4 h-4 text-[#F39C12]" />
                </div>
              ))}
            </div>
          </div>
        )}

        {/* 4. STEP 01: LESSON VIDEO & OBJECTIVES */}
        {view === 'step-video' && (
          <div className="space-y-6">
            <button onClick={() => setView('day-hub')} className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white">
              <ArrowLeft className="w-4 h-4" /> Back to Lesson 1 Steps
            </button>

            <div className="relative rounded-3xl overflow-hidden border border-[#24304D] h-64 bg-[#0B132B] flex flex-col items-center justify-center">
              <img src="/trainer_priya_banner.jpg" alt="Video Player" className="absolute inset-0 w-full h-full object-cover filter brightness-50" />
              <div className="relative z-10 flex flex-col items-center">
                <div className="w-16 h-16 rounded-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] flex items-center justify-center text-[#080C16] shadow-xl hover:scale-105 transition cursor-pointer">
                  <Play className="w-7 h-7 fill-current ml-1" />
                </div>
                <span className="text-xs font-bold text-white mt-3">Watch: Professional Introductions Masterclass (05:24)</span>
              </div>
            </div>

            <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-6 space-y-4">
              <h3 className="text-sm font-bold text-[#F39C12] uppercase tracking-wider">Today I will be able to…</h3>
              <ul className="space-y-2 text-xs text-gray-200">
                <li className="flex items-start gap-2">
                  <span className="text-[#F39C12] font-bold">✔</span>
                  <span>Use English confidently for professional introductions.</span>
                </li>
                <li className="flex items-start gap-2">
                  <span className="text-[#F39C12] font-bold">✔</span>
                  <span>Apply the target language in a realistic managerial situation.</span>
                </li>
                <li className="flex items-start gap-2">
                  <span className="text-[#F39C12] font-bold">✔</span>
                  <span>Speak clearly for 2–5 minutes with reduced hesitation.</span>
                </li>
              </ul>
            </div>

            <button
              onClick={() => setView('step-words')}
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
            >
              Next: Words for Work (8 Target Words) →
            </button>
          </div>
        )}

        {/* 5. STEP 02: WORDS FOR WORK */}
        {view === 'step-words' && (
          <div className="space-y-6">
            <button onClick={() => setView('day-hub')} className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white">
              <ArrowLeft className="w-4 h-4" /> Back to Lesson 1 Steps
            </button>

            <div>
              <span className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Step 02 • Target Vocabulary</span>
              <h2 className="text-xl font-bold">Words for Work (8 Words)</h2>
              <p className="text-xs text-gray-400 mt-1">Pronounce, define, and use these target words in managerial context.</p>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-3">
              {[
                { w: "role", def: "Position or function within a firm", ex: "My role involves coordinating deliveries." },
                { w: "department", def: "Functional division of an enterprise", ex: "I lead the Operations department." },
                { w: "experience", def: "Practical knowledge gained over time", ex: "I have eight years of experience." },
                { w: "responsibility", def: "A duty or accountability you oversee", ex: "My main responsibility is quality assurance." },
                { w: "position", def: "Formal job post or rank", ex: "She accepted the Senior Product Lead position." },
                { w: "team", def: "Colleagues working toward common goals", ex: "Our team consists of twelve specialists." },
                { w: "background", def: "Career history and education", ex: "My background is in supply chain logistics." },
                { w: "objective", def: "Specific measurable milestone", ex: "Our primary objective is customer satisfaction." }
              ].map((item, idx) => (
                <div key={idx} className="bg-[#131C31] border border-[#24304D] rounded-xl p-4 flex flex-col justify-between">
                  <div className="flex items-center justify-between">
                    <span className="text-base font-bold text-[#F39C12]">{item.w}</span>
                    <button className="text-gray-400 hover:text-[#F39C12]">
                      <Volume2 className="w-4 h-4" />
                    </button>
                  </div>
                  <p className="text-xs text-gray-300 mt-1">{item.def}</p>
                  <p className="text-[11px] text-gray-400 italic mt-2 bg-[#080C16] p-2 rounded border border-[#24304D]">
                    "{item.ex}"
                  </p>
                </div>
              ))}
            </div>

            <button
              onClick={() => setView('step-language')}
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
            >
              Next: Language Focus & Say It Phrases →
            </button>
          </div>
        )}

        {/* 6. STEP 03: LANGUAGE FOCUS & SAY IT */}
        {view === 'step-language' && (
          <div className="space-y-6">
            <button onClick={() => setView('day-hub')} className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white">
              <ArrowLeft className="w-4 h-4" /> Back to Lesson 1 Steps
            </button>

            <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-5 space-y-3">
              <span className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Useful Language Rules</span>
              <ul className="text-xs text-gray-300 space-y-2">
                <li className="flex items-center gap-2"><span className="text-[#F39C12]">✔</span> Use short, clear sentences.</li>
                <li className="flex items-center gap-2"><span className="text-[#F39C12]">✔</span> Choose the correct tense (Present Simple for daily roles).</li>
                <li className="flex items-center gap-2"><span className="text-[#F39C12]">✔</span> Ask follow-up questions to maintain rapport.</li>
                <li className="flex items-center gap-2"><span className="text-[#F39C12]">✔</span> Avoid translating word-for-word from your native language.</li>
              </ul>
            </div>

            <div>
              <h3 className="text-sm font-bold text-white uppercase tracking-wider mb-3">Say It — 6 Executive Phrases</h3>
              <div className="space-y-2.5">
                {[
                  "Let me explain that clearly.",
                  "Could you please clarify that?",
                  "My main responsibility is…",
                  "The priority is…",
                  "Let’s look at the best solution.",
                  "I’d like to make sure we agree on the next step."
                ].map((phrase, idx) => (
                  <div key={idx} className="bg-[#0B132B] border border-[#24304D] rounded-xl p-3.5 flex items-center justify-between">
                    <span className="text-sm font-semibold text-white">{phrase}</span>
                    <button className="text-[#F39C12] hover:scale-110 transition">
                      <Volume2 className="w-4 h-4" />
                    </button>
                  </div>
                ))}
              </div>
            </div>

            <button
              onClick={() => setView('step-controlled')}
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
            >
              Next: Controlled Practice Exercises →
            </button>
          </div>
        )}

        {/* 7. STEP 04: CONTROLLED PRACTICE */}
        {view === 'step-controlled' && (
          <div className="space-y-6">
            <button onClick={() => setView('day-hub')} className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white">
              <ArrowLeft className="w-4 h-4" /> Back to Lesson 1 Steps
            </button>

            <div>
              <span className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Step 04 • Practice Exercises</span>
              <h2 className="text-xl font-bold">Controlled Practice Drills</h2>
              <p className="text-xs text-gray-400 mt-1">Select the correct target vocabulary word to complete the sentence.</p>
            </div>

            <div className="space-y-4">
              {[
                { prompt: "My main ________ is leading the regional operations.", options: ["responsibility", "department", "objective"], selected: "responsibility" },
                { prompt: "Could you please ________ that project deadline?", options: ["clarify", "position", "team"], selected: "clarify" },
                { prompt: "Our primary ________ this quarter is client retention.", options: ["objective", "role", "background"], selected: "objective" }
              ].map((item, idx) => (
                <div key={idx} className="bg-[#131C31] border border-[#24304D] rounded-2xl p-4 space-y-3">
                  <p className="text-sm text-gray-200 font-medium">{idx + 1}. {item.prompt}</p>
                  <div className="flex gap-2">
                    {item.options.map(opt => (
                      <button
                        key={opt}
                        className={`px-4 py-1.5 rounded-full text-xs font-bold transition ${
                          opt === item.selected ? 'bg-[#F39C12] text-[#080C16]' : 'bg-[#080C16] border border-[#24304D] text-gray-300'
                        }`}
                      >
                        {opt}
                      </button>
                    ))}
                  </div>
                </div>
              ))}
            </div>

            <button
              onClick={() => setView('step-roleplay')}
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
            >
              Next: Role-Play Area (With AI & Without AI) →
            </button>
          </div>
        )}

        {/* 8. STEP 05: ROLE PLAY AREA (WITH AI & WITHOUT AI) */}
        {view === 'step-roleplay' && (
          <div className="space-y-6">
            <button onClick={() => setView('day-hub')} className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white">
              <ArrowLeft className="w-4 h-4" /> Back to Lesson 1 Steps
            </button>

            {/* Visual Role-Play Image Banner */}
            <div className="relative rounded-2xl overflow-hidden border border-[#24304D] h-36">
              <img src="/roleplay_meeting.jpg" alt="Role-play Scenario" className="w-full h-full object-cover" />
              <div className="absolute inset-0 bg-gradient-to-t from-[#080C16] via-[#080C16]/60 to-transparent p-4 flex flex-col justify-end">
                <span className="text-[10px] font-bold text-[#F39C12] uppercase tracking-wider">Trainer Guide Scenario</span>
                <h3 className="text-lg font-bold">Manager & Colleague Introduction</h3>
                <p className="text-[11px] text-gray-300">Explain your role, ask 3 follow-up questions, and agree on next step.</p>
              </div>
            </div>

            {/* Mode Switcher */}
            <div className="grid grid-cols-2 gap-3 bg-[#0B132B] p-1.5 rounded-2xl border border-[#24304D]">
              <button
                onClick={() => setRolePlayMode('without-ai')}
                className={`py-2.5 rounded-xl text-xs font-bold transition flex items-center justify-center gap-2 ${
                  rolePlayMode === 'without-ai' 
                    ? 'bg-[#F39C12] text-[#080C16] shadow-md' 
                    : 'text-gray-400 hover:text-white'
                }`}
              >
                <span>Drill Mode (Without AI)</span>
              </button>
              <button
                onClick={() => setRolePlayMode('with-ai')}
                className={`py-2.5 rounded-xl text-xs font-bold transition flex items-center justify-center gap-2 ${
                  rolePlayMode === 'with-ai' 
                    ? 'bg-[#F39C12] text-[#080C16] shadow-md' 
                    : 'text-gray-400 hover:text-white'
                }`}
              >
                <Sparkles className="w-3.5 h-3.5" />
                <span>Coach Mode (Google GenAI)</span>
              </button>
            </div>

            {/* MODE A: WITHOUT AI */}
            {rolePlayMode === 'without-ai' && (
              <div className="space-y-4">
                <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-5 space-y-3">
                  <h4 className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Structured Dialogue Script</h4>
                  
                  <div className="space-y-3">
                    <div className="bg-[#0B132B] p-3 rounded-xl border border-[#F39C12]/40">
                      <span className="text-[11px] font-bold text-[#F39C12]">You (Manager):</span>
                      <p className="text-xs text-white mt-1">"Good morning! My name is Priya, and I oversee the Operations department."</p>
                    </div>

                    <div className="bg-[#080C16] p-3 rounded-xl border border-[#24304D]">
                      <span className="text-[11px] font-bold text-gray-400">Colleague (Logistics):</span>
                      <p className="text-xs text-gray-200 mt-1">"Good morning Priya, great to meet you! I recently joined the Logistics team."</p>
                    </div>

                    <div className="bg-[#0B132B] p-3 rounded-xl border border-[#F39C12]/40">
                      <span className="text-[11px] font-bold text-[#F39C12]">You (Manager - Follow-up Question):</span>
                      <p className="text-xs text-white mt-1">"Could you please clarify your main responsibilities for this quarter?"</p>
                    </div>
                  </div>
                </div>

                <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-6 flex flex-col items-center text-center">
                  <div className="w-16 h-16 rounded-full bg-[#F39C12] flex items-center justify-center text-[#080C16] shadow-lg cursor-pointer hover:scale-105 transition">
                    <Mic className="w-7 h-7" />
                  </div>
                  <span className="text-xs font-bold text-white mt-3">Practice Speaking (2–5 Minutes)</span>
                  <p className="text-[11px] text-gray-400 mt-1">Record yourself speaking without reading, then self-audit your hesitation.</p>
                </div>
              </div>
            )}

            {/* MODE B: WITH AI (GOOGLE GENAI + TOKEN TELEMETRY) */}
            {rolePlayMode === 'with-ai' && (
              <div className="space-y-4">
                <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-5 space-y-3">
                  <div className="flex items-center justify-between">
                    <div className="flex items-center gap-2">
                      <Sparkles className="w-4 h-4 text-[#F39C12]" />
                      <h4 className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Google AI Studio Coach</h4>
                    </div>
                    <span className="text-[10px] bg-emerald-950/80 text-emerald-400 px-2 py-0.5 rounded border border-emerald-700">Gemini 2.5 Flash</span>
                  </div>

                  <div>
                    <label className="block text-[11px] font-semibold text-gray-400 mb-1">
                      API Key (Optional: uses server key if blank)
                    </label>
                    <input
                      type="password"
                      placeholder="Paste your AI Studio Key here..."
                      value={aiApiKey}
                      onChange={e => setAiApiKey(e.target.value)}
                      className="w-full bg-[#080C16] border border-[#24304D] rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-[#F39C12]"
                    />
                  </div>

                  <div>
                    <label className="block text-[11px] font-semibold text-gray-400 mb-1">
                      What did you say to your colleague?
                    </label>
                    <textarea
                      rows={3}
                      value={spokenText}
                      onChange={e => setSpokenText(e.target.value)}
                      className="w-full bg-[#080C16] border border-[#24304D] rounded-xl p-3 text-xs text-white focus:outline-none focus:border-[#F39C12]"
                    />
                  </div>

                  <button
                    onClick={handleSendToAi}
                    disabled={isAiLoading}
                    className="w-full bg-[#F39C12] text-[#080C16] font-bold py-2.5 rounded-xl text-xs flex items-center justify-center gap-2 hover:opacity-95 transition disabled:opacity-50"
                  >
                    {isAiLoading ? <RefreshCw className="w-4 h-4 animate-spin" /> : <Send className="w-4 h-4" />}
                    <span>{isAiLoading ? "Analyzing..." : "Evaluate Managerial Introduction"}</span>
                  </button>
                </div>

                {aiError && (
                  <div className="bg-red-950/40 border border-red-700/60 rounded-2xl p-4 text-xs text-red-200 flex items-start gap-3">
                    <span className="text-base">⚠️</span>
                    <div>
                      <strong className="block text-red-300 font-bold mb-1">AI Coach Not Connected</strong>
                      <p>{aiError}</p>
                    </div>
                  </div>
                )}

                {aiFeedback && (
                  <div className="bg-[#131C31] border border-[#F39C12] rounded-2xl p-5 space-y-4 shadow-[0_0_20px_rgba(243,156,18,0.15)] animate-fade-in">
                    <div>
                      <span className="text-[10px] font-bold text-[#F39C12] uppercase tracking-wider">AI Colleague Dialogue</span>
                      <p className="text-xs text-white font-medium italic mt-1 bg-[#080C16] p-3 rounded-xl border border-[#24304D]">
                        "{aiFeedback.reply}"
                      </p>
                    </div>

                    <div>
                      <span className="text-[10px] font-bold text-gray-400 uppercase tracking-wider">Polished Executive Phrasing</span>
                      <p className="text-xs text-emerald-300 mt-1 bg-[#080C16] p-3 rounded-xl border border-emerald-900/40">
                        {aiFeedback.improved}
                      </p>
                    </div>

                    {/* Token telemetry widget */}
                    <div className="pt-3 border-t border-[#24304D] flex items-center justify-between text-xs">
                      <div className="flex items-center gap-1.5 text-gray-300">
                        <Activity className="w-4 h-4 text-[#F39C12]" />
                        <span>Fluency: <strong className="text-[#F39C12]">{aiFeedback.fluency}/5</strong></span>
                      </div>
                      <div className="flex items-center gap-1.5 text-gray-300">
                        <Zap className="w-4 h-4 text-cyan-400" />
                        <span>Telemetry: <strong className="text-white">{aiFeedback.tokens} tokens</strong> (~${aiFeedback.cost})</span>
                      </div>
                    </div>
                  </div>
                )}
              </div>
            )}

            <button
              onClick={() => setView('step-quiz')}
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
            >
              Next: Check Your Understanding (Quiz) →
            </button>
          </div>
        )}

        {/* 9. STEP 06: QUIZ */}
        {view === 'step-quiz' && (
          <div className="space-y-6">
            <button onClick={() => setView('day-hub')} className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white">
              <ArrowLeft className="w-4 h-4" /> Back to Lesson 1 Steps
            </button>

            <div>
              <span className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Step 06 • Comprehension</span>
              <h2 className="text-xl font-bold">Lesson 1 Quiz</h2>
              <p className="text-xs text-gray-400 mt-1">Answer questions to verify vocabulary and phrase mastery.</p>
            </div>

            <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-6 space-y-4">
              <span className="text-xs font-bold text-[#F39C12]">Q1 of 5</span>
              <h4 className="text-sm font-bold text-white">Which sentence uses the target word 'responsibility' correctly?</h4>

              <div className="space-y-2.5">
                {[
                  { text: 'My main responsibility is overseeing cross-functional projects.', selected: true },
                  { text: 'I have responsibility my breakfast every morning.', selected: false },
                  { text: 'The responsibility car was delayed on the highway.', selected: false },
                  { text: 'He responsibled to the vice president yesterday.', selected: false }
                ].map((opt, i) => (
                  <div
                    key={i}
                    className={`p-3.5 rounded-xl border flex items-center gap-3 cursor-pointer transition ${
                      opt.selected ? 'bg-[#0B132B] border-[#F39C12] text-white' : 'bg-[#080C16] border-[#24304D] text-gray-400'
                    }`}
                  >
                    <div className={`w-4 h-4 rounded-full border flex items-center justify-center ${
                      opt.selected ? 'border-[#F39C12]' : 'border-gray-600'
                    }`}>
                      {opt.selected && <div className="w-2 h-2 rounded-full bg-[#F39C12]" />}
                    </div>
                    <span className="text-xs font-medium">{opt.text}</span>
                  </div>
                ))}
              </div>
            </div>

            <button
              onClick={() => setView('step-completed')}
              className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
            >
              Complete Lesson & Self-Audit →
            </button>
          </div>
        )}

        {/* 10. STEP 07: CELEBRATION & AUDIT */}
        {view === 'step-completed' && (
          <div className="space-y-6 text-center py-4">
            <div className="w-20 h-20 rounded-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] flex items-center justify-center mx-auto text-[#080C16] shadow-2xl">
              <Award className="w-10 h-10" />
            </div>

            <div>
              <h2 className="text-2xl font-bold">Lesson 1 Completed!</h2>
              <p className="text-xs text-gray-400 mt-1">Professional Introductions • Module 1 Week 1</p>
            </div>

            <div className="grid grid-cols-3 gap-3 max-w-sm mx-auto">
              <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-3">
                <span className="text-sm font-bold text-[#F39C12] block">7 / 7</span>
                <span className="text-[10px] text-gray-400">Steps Done</span>
              </div>
              <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-3">
                <span className="text-sm font-bold text-[#F39C12] block">100%</span>
                <span className="text-[10px] text-gray-400">Score</span>
              </div>
              <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-3">
                <span className="text-sm font-bold text-[#F39C12] block">+50</span>
                <span className="text-[10px] text-gray-400">Points</span>
              </div>
            </div>

            {/* Reflection card */}
            <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-5 text-left max-w-md mx-auto space-y-3">
              <h4 className="text-xs font-bold text-[#F39C12] uppercase tracking-wider">Lesson Reflection (From Learner Book)</h4>
              <div>
                <span className="text-[11px] text-gray-400">One phrase I will use this week:</span>
                <p className="text-xs text-white font-medium mt-0.5">"Let’s look at the best solution."</p>
              </div>
              <div>
                <span className="text-[11px] text-gray-400">One mistake I want to avoid:</span>
                <p className="text-xs text-white font-medium mt-0.5">"Translating word-for-word from native language."</p>
              </div>
              <div>
                <span className="text-[11px] text-gray-400">Confidence today:</span>
                <p className="text-xs text-[#F39C12] font-bold mt-0.5">⭐⭐⭐⭐⭐ (5 / 5)</p>
              </div>
            </div>

            <div className="space-y-3 max-w-md mx-auto pt-2">
              <button
                onClick={() => setView('week-plan')}
                className="w-full bg-gradient-to-r from-[#E5A638] via-[#F39C12] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-2xl text-sm transition hover:opacity-95 shadow-[0_4px_16px_rgba(243,156,18,0.35)]"
              >
                Continue to Day 2: Job Roles →
              </button>
              <button
                onClick={() => setView('week-plan')}
                className="w-full bg-[#131C31] border border-[#24304D] text-white font-semibold py-3 rounded-2xl text-xs hover:bg-[#18233C] transition"
              >
                ← Back to Week 1
              </button>
            </div>
          </div>
        )}

      </main>
    </div>
  );
}
