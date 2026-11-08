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
  LogOut
} from 'lucide-react';

export function App() {
  // Auth state
  const [user, setUser] = useState<{ email: string; role: 'LEARNER' | 'TRAINER'; name: string } | null>(null);
  const [emailInput, setEmailInput] = useState('user@elevora.com');
  const [passwordInput, setPasswordInput] = useState('user123');
  const [authError, setAuthError] = useState('');

  // Course navigation state
  // 'modules' | 'module-overview' | 'week-plan' | 'day-hub' | 'lesson-video' | 'key-expressions' | 'speak-repeat' | 'quick-activity' | 'quiz' | 'day-completed'
  const [view, setView] = useState<string>('module-overview');

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (emailInput === 'admin@elevora.com' && passwordInput === 'admin123') {
      setUser({ email: 'admin@elevora.com', role: 'TRAINER', name: 'Elevora Admin Trainer' });
      setAuthError('');
    } else if (emailInput === 'user@elevora.com' && passwordInput === 'user123') {
      setUser({ email: 'user@elevora.com', role: 'LEARNER', name: 'Priya Sharma' });
      setAuthError('');
    } else {
      setAuthError('Invalid credentials. Use user@elevora.com / user123 or admin@elevora.com / admin123');
    }
  };

  // If not logged in, render Login
  if (!user) {
    return (
      <div className="min-h-screen bg-[#080C16] text-white flex items-center justify-center p-4">
        <div className="w-full max-w-md bg-[#131C31] border border-[#24304D] rounded-2xl p-8 shadow-2xl">
          <div className="flex flex-col items-center mb-6">
            <div className="w-16 h-16 rounded-2xl bg-[#0B132B] border border-[#F39C12] flex items-center justify-center font-bold text-2xl text-[#F39C12] mb-3">
              E
            </div>
            <h1 className="text-2xl font-bold">Elevora Learning</h1>
            <p className="text-sm text-gray-400">Sign in to your learning account</p>
          </div>

          {authError && (
            <div className="mb-4 p-3 bg-red-950 border border-red-700 rounded-lg text-xs text-red-200">
              {authError}
            </div>
          )}

          <form onSubmit={handleLogin} className="space-y-4">
            <div>
              <label className="block text-xs font-semibold text-gray-300 mb-1">Email</label>
              <input
                type="email"
                value={emailInput}
                onChange={e => setEmailInput(e.target.value)}
                className="w-full bg-[#080C16] border border-[#24304D] rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none focus:border-[#F39C12]"
                placeholder="name@elevora.com"
                required
              />
            </div>

            <div>
              <label className="block text-xs font-semibold text-gray-300 mb-1">Password</label>
              <input
                type="password"
                value={passwordInput}
                onChange={e => setPasswordInput(e.target.value)}
                className="w-full bg-[#080C16] border border-[#24304D] rounded-xl px-4 py-2.5 text-sm text-white focus:outline-none focus:border-[#F39C12]"
                placeholder="••••••••"
                required
              />
            </div>

            <button
              type="submit"
              className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3 rounded-xl transition hover:opacity-95 text-sm"
            >
              Sign In
            </button>
          </form>

          <div className="mt-6 pt-4 border-t border-[#24304D] text-xs text-gray-400 space-y-1">
            <p className="font-semibold text-gray-300">Quick Credentials:</p>
            <p>• Learner: <code className="text-amber-400">user@elevora.com</code> / <code className="text-amber-400">user123</code></p>
            <p>• Trainer: <code className="text-amber-400">admin@elevora.com</code> / <code className="text-amber-400">admin123</code></p>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-[#080C16] text-white flex flex-col">
      {/* Top Navigation Bar */}
      <header className="h-16 border-b border-[#24304D] bg-[#0B132B] px-6 flex items-center justify-between sticky top-0 z-50">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-xl bg-[#080C16] border border-[#F39C12] flex items-center justify-center font-bold text-lg text-[#F39C12]">
            E
          </div>
          <div>
            <h1 className="text-base font-bold leading-tight">Elevora</h1>
            <p className="text-xs text-gray-400">Managerial English Development</p>
          </div>
        </div>

        <div className="flex items-center gap-4">
          <div className="flex items-center gap-2 bg-[#131C31] border border-[#24304D] rounded-full px-3 py-1.5">
            {user.role === 'TRAINER' ? (
              <Shield className="w-4 h-4 text-[#F39C12]" />
            ) : (
              <User className="w-4 h-4 text-[#F39C12]" />
            )}
            <span className="text-xs font-semibold">{user.name}</span>
            <span className="text-[10px] bg-[#F39C12]/20 text-[#F39C12] font-bold px-2 py-0.5 rounded-full">
              {user.role}
            </span>
          </div>

          <button
            onClick={() => setUser(null)}
            className="p-2 rounded-full bg-[#131C31] hover:bg-red-950/40 border border-[#24304D] text-gray-400 hover:text-amber-400"
            title="Log Out"
          >
            <LogOut className="w-4 h-4" />
          </button>
        </div>
      </header>

      {/* Main Container */}
      <main className="flex-1 max-w-4xl w-full mx-auto p-4 md:p-6">
        {/* VIEW 1: MODULE OVERVIEW (Screenshot 2) */}
        {view === 'module-overview' && (
          <div className="space-y-6">
            {/* Hero Card */}
            <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-6 relative overflow-hidden">
              <div className="flex items-start gap-4">
                <div className="w-14 h-14 rounded-xl bg-[#0B132B] border border-[#F39C12] flex items-center justify-center font-bold text-2xl text-[#F39C12] shrink-0">
                  M1
                </div>
                <div>
                  <span className="text-xs font-bold text-[#F39C12]">Module 1</span>
                  <h2 className="text-xl font-bold text-white mt-0.5">
                    Building Confidence & Core Workplace English
                  </h2>
                  <p className="text-xs text-gray-400 mt-2">
                    Build the essential English skills you need for everyday workplace success.
                  </p>
                  <div className="flex items-center gap-4 mt-4 text-xs text-gray-300">
                    <span className="bg-[#0B132B] px-2.5 py-1 rounded-md border border-[#24304D]">📅 4 Weeks</span>
                    <span className="bg-[#0B132B] px-2.5 py-1 rounded-md border border-[#24304D]">📖 12 Lessons</span>
                    <span className="bg-[#0B132B] px-2.5 py-1 rounded-md border border-[#24304D]">📊 Beginner</span>
                  </div>
                </div>
              </div>
            </div>

            {/* Weeks in this Module */}
            <div>
              <h3 className="text-sm font-bold text-gray-300 uppercase tracking-wider mb-3">Weeks in this Module</h3>
              <div className="space-y-3">
                <div 
                  onClick={() => setView('week-plan')}
                  className="bg-[#131C31] border border-[#F39C12] rounded-xl p-4 flex items-center justify-between cursor-pointer hover:bg-[#18233C] transition"
                >
                  <div className="flex items-center gap-3">
                    <BookOpen className="w-5 h-5 text-[#F39C12]" />
                    <div>
                      <span className="text-xs font-bold text-[#F39C12] block">Week 1</span>
                      <span className="text-sm font-semibold text-white">Getting Started & Everyday English</span>
                    </div>
                  </div>
                  <ChevronRight className="w-5 h-5 text-[#F39C12]" />
                </div>

                {['Week 2: Workplace Foundations', 'Week 3: Meetings & Collaboration', 'Week 4: Professional Writing'].map((w, idx) => (
                  <div key={idx} className="bg-[#050810] border border-[#24304D] rounded-xl p-4 flex items-center justify-between opacity-60">
                    <div className="flex items-center gap-3">
                      <Lock className="w-5 h-5 text-gray-500" />
                      <span className="text-sm font-medium text-gray-400">{w}</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        )}

        {/* VIEW 2: WEEK 1 DAYS (Screenshot 3) */}
        {view === 'week-plan' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('module-overview')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Module 1
            </button>

            <div className="flex gap-2">
              {['Week 1', 'Week 2', 'Week 3', 'Week 4'].map((w, i) => (
                <button
                  key={i}
                  className={`px-4 py-2 rounded-xl text-xs font-bold ${
                    i === 0 ? 'bg-[#F39C12] text-[#080C16]' : 'bg-[#131C31] border border-[#24304D] text-gray-400'
                  }`}
                >
                  {w}
                </button>
              ))}
            </div>

            <div>
              <h2 className="text-xl font-bold">Week 1</h2>
              <p className="text-xs text-gray-400">Getting Started & Everyday English</p>
            </div>

            <div className="space-y-3">
              <div 
                onClick={() => setView('day-hub')}
                className="bg-[#131C31] border border-[#F39C12] rounded-xl p-4 flex items-center justify-between cursor-pointer hover:bg-[#18233C] transition"
              >
                <div className="flex items-center gap-3">
                  <div className="w-9 h-9 rounded-full bg-[#F39C12]/20 border border-[#F39C12] flex items-center justify-center text-[#F39C12]">
                    <Play className="w-4 h-4 fill-current" />
                  </div>
                  <div>
                    <span className="text-xs font-bold text-[#F39C12] block">Day 1</span>
                    <span className="text-sm font-semibold text-white">Introduction & Goals</span>
                  </div>
                </div>
                <div className="w-8 h-8 rounded-full border border-[#F39C12] flex items-center justify-center text-[10px] font-bold text-[#F39C12]">
                  0%
                </div>
              </div>

              {['Day 2: Professional Introductions', 'Day 3: Talking About Your Work', 'Day 4: Workplace Vocabulary (Part 1)', 'Day 5: Small Talk at Work'].map((d, idx) => (
                <div key={idx} className="bg-[#050810] border border-[#24304D] rounded-xl p-4 flex items-center justify-between opacity-60">
                  <div className="flex items-center gap-3">
                    <div className="w-9 h-9 rounded-full bg-[#131C31] border border-[#24304D] flex items-center justify-center text-gray-500">
                      <Lock className="w-4 h-4" />
                    </div>
                    <span className="text-sm font-medium text-gray-400">{d}</span>
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* VIEW 3: DAY 1 HUB (Screenshot 4) */}
        {view === 'day-hub' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('week-plan')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Week 1
            </button>

            <div>
              <span className="bg-[#F39C12] text-[#080C16] text-[11px] font-bold px-2.5 py-1 rounded-md">Day 1</span>
              <h2 className="text-xl font-bold mt-2">Introduction & Goals</h2>
              <p className="text-xs text-gray-400 mt-1">
                Welcome to Elevora! Today, you'll understand the course, set your goals and learn key expressions.
              </p>
            </div>

            <div className="space-y-3">
              {[
                { id: 'lesson-video', title: 'Lesson', sub: 'Course Overview & Learning Goals', time: '(5 min)', icon: Play, current: true },
                { id: 'key-expressions', title: 'Learn', sub: 'Key Expressions – Getting Started', time: '', icon: BookOpen },
                { id: 'speak-repeat', title: 'Practice', sub: 'Speak & Repeat', time: '', icon: Mic },
                { id: 'quick-activity', title: 'Apply', sub: 'Quick Activity', time: '(3 min)', icon: CheckCircle },
                { id: 'quiz', title: 'Quiz', sub: 'Check Your Understanding', time: '(5 min)', icon: HelpCircle }
              ].map(step => (
                <div
                  key={step.id}
                  onClick={() => setView(step.id)}
                  className={`border rounded-xl p-4 flex items-center gap-4 cursor-pointer transition ${
                    step.current ? 'bg-[#131C31] border-[#F39C12] hover:bg-[#18233C]' : 'bg-[#050810] border-[#24304D] hover:bg-[#131C31]'
                  }`}
                >
                  <div className={`w-9 h-9 rounded-full flex items-center justify-center ${
                    step.current ? 'bg-[#F39C12]/20 border border-[#F39C12] text-[#F39C12]' : 'bg-[#131C31] border border-[#24304D] text-[#F39C12]'
                  }`}>
                    <step.icon className="w-4 h-4" />
                  </div>
                  <div>
                    <span className="text-xs font-bold text-[#F39C12]">{step.title} <span className="text-gray-500 font-normal">{step.time}</span></span>
                    <h4 className="text-sm font-semibold text-white">{step.sub}</h4>
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}

        {/* VIEW 4: LESSON VIDEO PLAYER (Screenshot 5) */}
        {view === 'lesson-video' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('day-hub')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Day 1 Hub
            </button>

            {/* Video Player */}
            <div className="w-full h-64 bg-[#0B132B] border border-[#24304D] rounded-2xl flex flex-col items-center justify-center relative">
              <div className="w-16 h-16 rounded-full bg-[#F39C12] flex items-center justify-center text-[#080C16] shadow-lg cursor-pointer hover:scale-105 transition">
                <Play className="w-8 h-8 fill-current ml-1" />
              </div>
              <span className="absolute bottom-4 left-4 text-xs text-gray-400 bg-black/60 px-2 py-1 rounded">
                0:00 / 5:14
              </span>
            </div>

            {/* Tab Info */}
            <div className="space-y-3">
              <h3 className="text-lg font-bold text-white">Course Overview & Learning Goals</h3>
              <p className="text-xs text-gray-400">In this lesson, we will:</p>
              <ul className="text-xs text-gray-300 space-y-1.5 list-disc pl-5">
                <li>Understand what you will learn in Module 1</li>
                <li>Know how the 4-week plan works (3 classes per week)</li>
                <li>Set your personal learning goals & targets</li>
                <li>Learn key expressions for professional introductions</li>
              </ul>
            </div>

            <button
              onClick={() => setView('key-expressions')}
              className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-xl text-sm transition hover:opacity-95"
            >
              Next: Key Expressions →
            </button>
          </div>
        )}

        {/* VIEW 5: KEY EXPRESSIONS (Screenshot 6) */}
        {view === 'key-expressions' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('day-hub')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Day 1 Hub
            </button>

            <div>
              <h2 className="text-xl font-bold">Key Expressions – Getting Started</h2>
              <p className="text-xs text-gray-400 mt-1">Here are some useful expressions to begin your professional English journey.</p>
            </div>

            <div className="space-y-3">
              {[
                "I'm looking forward to learning.",
                "This course will help me...",
                "My goal is to improve my communication.",
                "I want to be more confident at work.",
                "I can practise a little every day."
              ].map((expr, i) => (
                <div key={i} className="bg-[#0B132B] border border-[#24304D] rounded-xl p-4 flex items-center justify-between">
                  <span className="text-sm font-medium text-white">{expr}</span>
                  <button className="text-[#F39C12] hover:scale-110 transition">
                    <Volume2 className="w-5 h-5" />
                  </button>
                </div>
              ))}
            </div>

            <button
              onClick={() => setView('speak-repeat')}
              className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-xl text-sm transition hover:opacity-95"
            >
              Continue to Practice →
            </button>
          </div>
        )}

        {/* VIEW 6: SPEAK & REPEAT (Screenshot 7) */}
        {view === 'speak-repeat' && (
          <div className="space-y-6 text-center">
            <button 
              onClick={() => setView('day-hub')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white text-left"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Day 1 Hub
            </button>

            <div>
              <h2 className="text-xl font-bold">Speak & Repeat</h2>
              <p className="text-xs text-gray-400 mt-1">Listen to the sentence and repeat it clearly. Tap the mic and speak.</p>
            </div>

            <div className="bg-[#0B132B] border border-[#24304D] rounded-2xl p-8 flex flex-col items-center">
              <div className="w-12 h-12 rounded-full bg-[#F39C12]/20 border border-[#F39C12] flex items-center justify-center text-[#F39C12] mb-4">
                <Volume2 className="w-6 h-6" />
              </div>
              <h3 className="text-lg font-bold text-white max-w-sm">"I'm looking forward to learning."</h3>
            </div>

            <div className="flex items-center justify-center py-6">
              <button className="w-20 h-20 rounded-full bg-[#F39C12] text-[#080C16] flex items-center justify-center shadow-lg hover:scale-105 transition">
                <Mic className="w-8 h-8" />
              </button>
            </div>

            <button
              onClick={() => setView('quick-activity')}
              className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-xl text-sm transition hover:opacity-95"
            >
              Continue to Quick Activity →
            </button>
          </div>
        )}

        {/* VIEW 7: QUICK ACTIVITY (Screenshot 8) */}
        {view === 'quick-activity' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('day-hub')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Day 1 Hub
            </button>

            <div>
              <h2 className="text-xl font-bold">Quick Activity</h2>
              <p className="text-xs text-gray-400 mt-1">Complete the sentences with the right words.</p>
            </div>

            <div className="space-y-6">
              {[
                { q: "1. My goal is to ________ my communication skills.", pills: ["improve", "moves", "change"], chosen: "improve" },
                { q: "2. I'm ________ forward to learning.", pills: ["look", "looking", "looked"], chosen: "looking" },
                { q: "3. This course will ________ me at work.", pills: ["help", "helps", "helping"], chosen: "help" }
              ].map((item, idx) => (
                <div key={idx} className="space-y-2">
                  <p className="text-sm font-medium text-white">{item.q}</p>
                  <div className="flex gap-2">
                    {item.pills.map((p, pIdx) => (
                      <button
                        key={pIdx}
                        className={`px-4 py-1.5 rounded-full text-xs font-bold ${
                          p === item.chosen ? 'bg-[#F39C12] text-[#080C16]' : 'bg-[#131C31] border border-[#24304D] text-gray-300'
                        }`}
                      >
                        {p}
                      </button>
                    ))}
                  </div>
                </div>
              ))}
            </div>

            <button
              onClick={() => setView('quiz')}
              className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-xl text-sm transition hover:opacity-95 mt-8"
            >
              Check Answers & Start Quiz →
            </button>
          </div>
        )}

        {/* VIEW 8: QUIZ (Screenshot 9) */}
        {view === 'quiz' && (
          <div className="space-y-6">
            <button 
              onClick={() => setView('day-hub')}
              className="flex items-center gap-2 text-xs font-semibold text-gray-400 hover:text-white"
            >
              <ArrowLeft className="w-4 h-4" /> Back to Day 1 Hub
            </button>

            <div>
              <h2 className="text-xl font-bold">Day 1 Quiz</h2>
              <p className="text-xs text-gray-400 mt-1">Answer 5 quick questions to check your understanding.</p>
            </div>

            <div className="bg-[#131C31] border border-[#24304D] rounded-2xl p-6 space-y-4">
              <span className="text-xs font-bold text-[#F39C12]">Q1 of 5</span>
              <h4 className="text-base font-semibold text-white">What is the main goal of Module 1?</h4>

              <div className="space-y-2.5">
                {[
                  { text: 'To improve professional English communication', selected: true },
                  { text: 'To learn advanced grammar only', selected: false },
                  { text: 'To prepare for an exam', selected: false },
                  { text: 'To write long reports', selected: false }
                ].map((opt, i) => (
                  <div
                    key={i}
                    className={`p-3 rounded-xl border flex items-center gap-3 cursor-pointer ${
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
              onClick={() => setView('day-completed')}
              className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-xl text-sm transition hover:opacity-95"
            >
              Next →
            </button>
          </div>
        )}

        {/* VIEW 9: DAY COMPLETED CELEBRATION (Screenshot 10) */}
        {view === 'day-completed' && (
          <div className="text-center space-y-6 py-8">
            <div className="w-20 h-20 rounded-full bg-[#F39C12] flex items-center justify-center mx-auto text-[#080C16] shadow-xl">
              <Award className="w-10 h-10" />
            </div>

            <div>
              <h2 className="text-2xl font-bold">Day 1 Completed!</h2>
              <p className="text-xs text-gray-400 mt-1">Great start! You've completed Introduction & Goals.</p>
            </div>

            <div className="grid grid-cols-3 gap-3 max-w-sm mx-auto">
              <div className="bg-[#131C31] border border-[#24304D] rounded-xl p-3">
                <span className="text-sm font-bold text-[#F39C12] block">5 / 5</span>
                <span className="text-[10px] text-gray-400">Activities</span>
              </div>
              <div className="bg-[#131C31] border border-[#24304D] rounded-xl p-3">
                <span className="text-sm font-bold text-[#F39C12] block">100%</span>
                <span className="text-[10px] text-gray-400">Score</span>
              </div>
              <div className="bg-[#131C31] border border-[#24304D] rounded-xl p-3">
                <span className="text-sm font-bold text-[#F39C12] block">+50</span>
                <span className="text-[10px] text-gray-400">Points</span>
              </div>
            </div>

            <div className="pt-6 space-y-3">
              <button
                onClick={() => setView('week-plan')}
                className="w-full bg-gradient-to-r from-[#E5A638] to-[#FBD07A] text-[#080C16] font-bold py-3.5 rounded-xl text-sm transition hover:opacity-95"
              >
                Continue to Day 2 →
              </button>

              <button
                onClick={() => setView('week-plan')}
                className="w-full bg-[#131C31] border border-[#24304D] text-white font-semibold py-3 rounded-xl text-sm transition hover:bg-[#18233C]"
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

