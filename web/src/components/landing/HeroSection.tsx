import React from 'react';
import { 
  Briefcase, 
  ArrowRight, 
  Play, 
  Check, 
  MessageSquare, 
  Target, 
  Bot, 
  BarChart2 
} from 'lucide-react';
import { AppPhoneMockup } from './AppPhoneMockup';

interface HeroSectionProps {
  onStartLearning: () => void;
  onWatchDemo: () => void;
}

export const HeroSection: React.FC<HeroSectionProps> = ({ onStartLearning, onWatchDemo }) => {
  return (
    <section id="hero" className="relative pt-8 pb-16 md:pt-14 md:pb-24 bg-[#060D1E] overflow-hidden">
      
      {/* Background yellow organic shape on the right like the reference image */}
      <div className="absolute right-0 top-10 w-[520px] h-[550px] pointer-events-none z-0 hidden lg:block">
        <svg viewBox="0 0 500 500" fill="none" className="w-full h-full opacity-95">
          <path 
            d="M120,60 C260,10 420,50 470,160 C520,270 490,420 370,470 C250,520 80,470 30,360 C-20,250 -20,110 120,60 Z" 
            fill="#F5A623" 
          />
        </svg>
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative z-10">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
          
          {/* Left Column: Typography matching exact reference */}
          <div className="lg:col-span-6 space-y-6">
            
            {/* Small pill: Briefcase + "Professional English Learning Platform" */}
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-[#121E36] border border-slate-700/60 text-slate-300 text-xs font-medium">
              <span className="text-amber-400">💼</span>
              <span>Professional English Learning Platform</span>
            </div>

            {/* Headline: Speak English Confidently at Work (with yellow text & curved underline) */}
            <h1 className="text-4xl sm:text-5xl lg:text-6xl font-black text-white tracking-tight leading-[1.12] font-display">
              Speak English <br />
              Confidently{' '}
              <span className="relative inline-block text-[#F5A623]">
                at Work
                {/* Yellow curved drawn scribble underline matching mockup */}
                <svg className="absolute -bottom-2 left-0 w-full h-3 text-[#F5A623]" viewBox="0 0 170 12" fill="none">
                  <path d="M2.5 8.5C45 2.5 125 1.5 167.5 9" stroke="#F5A623" strokeWidth="4" strokeLinecap="round" />
                </svg>
              </span>
            </h1>

            {/* Subtitle */}
            <p className="text-sm sm:text-base text-slate-300 max-w-xl leading-relaxed">
              A structured, practical and AI-powered learning journey to build real workplace communication skills.
            </p>

            {/* 4 Feature Badges with Yellow Icons */}
            <div className="grid grid-cols-2 sm:grid-cols-4 gap-3 pt-1">
              <div className="flex items-center gap-2 text-slate-200 text-xs font-semibold">
                <span className="text-amber-400 text-base">💬</span>
                <span className="leading-tight">Workplace Conversations</span>
              </div>
              <div className="flex items-center gap-2 text-slate-200 text-xs font-semibold">
                <span className="text-amber-400 text-base">🎯</span>
                <span className="leading-tight">Real-world Practice</span>
              </div>
              <div className="flex items-center gap-2 text-slate-200 text-xs font-semibold">
                <span className="text-amber-400 text-base">🤖</span>
                <span className="leading-tight">AI Speaking Tutor</span>
              </div>
              <div className="flex items-center gap-2 text-slate-200 text-xs font-semibold">
                <span className="text-amber-400 text-base">📈</span>
                <span className="leading-tight">Track Your Progress</span>
              </div>
            </div>

            {/* Buttons: Gold pill "Start Learning Now →" & Dark pill "▶ Watch Demo (2 min)" */}
            <div className="flex flex-wrap items-center gap-4 pt-3">
              <button
                onClick={onStartLearning}
                className="px-8 py-3.5 rounded-full text-sm font-bold text-slate-950 bg-[#F5A623] hover:bg-[#e0951b] transition-all flex items-center gap-2 shadow-lg shadow-amber-500/20 active:scale-98"
              >
                <span>Start Learning Now</span>
                <span className="text-base font-black">→</span>
              </button>

              <button
                onClick={onWatchDemo}
                className="px-6 py-3.5 rounded-full text-sm font-semibold text-white bg-[#0A1224] hover:bg-[#121E36] border border-slate-700 transition-all flex items-center gap-2"
              >
                <span className="w-5 h-5 rounded-full border border-slate-500 flex items-center justify-center text-[9px] text-amber-400">▶</span>
                <span>Watch Demo (2 min)</span>
              </button>
            </div>

            {/* Micro checklist with yellow checks: ✓ Self-paced   ✓ Mobile Friendly   ✓ Industry Relevant */}
            <div className="flex flex-wrap items-center gap-5 pt-1 text-xs text-slate-300 font-medium">
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-bold">✓</span>
                <span>Self-paced</span>
              </div>
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-bold">✓</span>
                <span>Mobile Friendly</span>
              </div>
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-bold">✓</span>
                <span>Industry Relevant</span>
              </div>
            </div>

          </div>

          {/* Right Column: Phone Mockup + Woman in Navy Suit exactly matching composition */}
          <div className="lg:col-span-6 relative flex items-center justify-center lg:justify-end">
            
            <div className="relative flex items-center">
              
              {/* Phone Mockup (Center Left) */}
              <div className="relative z-20 mr-[-50px] sm:mr-[-70px]">
                <AppPhoneMockup onInteract={onStartLearning} />
              </div>

              {/* Executive Woman Portrait (Right side) */}
              <div className="relative z-10 w-[260px] sm:w-[320px] lg:w-[340px] select-none">
                <img 
                  src="/assets/hero-executive.jpg" 
                  alt="Elevora Confident Executive" 
                  className="w-full h-auto object-contain drop-shadow-2xl"
                />
              </div>

            </div>

          </div>

        </div>
      </div>
    </section>
  );
};
