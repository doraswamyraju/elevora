import React from 'react';
import { 
  ArrowRight, 
  Play, 
  MessageSquare, 
  Briefcase, 
  Bot, 
  BarChart, 
  CheckCircle,
  Sparkles
} from 'lucide-react';
import { AppPhoneMockup } from './AppPhoneMockup';

interface HeroSectionProps {
  onStartLearning: () => void;
  onWatchDemo: () => void;
}

export const HeroSection: React.FC<HeroSectionProps> = ({ onStartLearning, onWatchDemo }) => {
  return (
    <section id="hero" className="relative pt-12 pb-20 md:pt-20 md:pb-32 overflow-hidden">
      {/* Background radial glow */}
      <div className="absolute top-1/4 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[700px] h-[500px] bg-amber-500/10 rounded-full blur-[140px] pointer-events-none -z-10" />
      <div className="absolute top-1/3 right-10 w-[400px] h-[400px] bg-blue-600/10 rounded-full blur-[120px] pointer-events-none -z-10" />

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 lg:gap-8 items-center">
          
          {/* Left Column: Headline & Value Prop */}
          <div className="lg:col-span-7 space-y-8 text-center lg:text-left">
            {/* Pill Tag */}
            <div className="inline-flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-amber-500/10 border border-amber-500/25 text-amber-300 text-xs sm:text-sm font-semibold tracking-wide">
              <Briefcase className="w-4 h-4 text-amber-400" />
              <span>Professional English Learning Platform</span>
            </div>

            {/* Main Headline */}
            <h1 className="text-4xl sm:text-5xl lg:text-6xl font-extrabold tracking-tight text-white leading-[1.12] font-display">
              Speak English <br />
              Confidently <span className="text-transparent bg-clip-text bg-gradient-to-r from-amber-400 via-yellow-300 to-amber-500 underline decoration-amber-500/50 decoration-wavy decoration-2">at Work</span>
            </h1>

            {/* Subtitle */}
            <p className="text-base sm:text-lg lg:text-xl text-slate-300 max-w-2xl mx-auto lg:mx-0 font-normal leading-relaxed">
              A structured, practical and AI-powered learning journey to build real workplace communication skills — designed specifically for managers, team leads, and rising professionals.
            </p>

            {/* 4 Feature Highlights Grid */}
            <div className="grid grid-cols-2 sm:grid-cols-4 gap-4 pt-2">
              <div className="flex items-center gap-2 text-slate-300 text-xs sm:text-sm font-medium">
                <div className="w-7 h-7 rounded-lg bg-amber-500/15 border border-amber-500/30 flex items-center justify-center text-amber-400 shrink-0">
                  <MessageSquare className="w-3.5 h-3.5" />
                </div>
                <span>Workplace Conversations</span>
              </div>
              <div className="flex items-center gap-2 text-slate-300 text-xs sm:text-sm font-medium">
                <div className="w-7 h-7 rounded-lg bg-amber-500/15 border border-amber-500/30 flex items-center justify-center text-amber-400 shrink-0">
                  <Briefcase className="w-3.5 h-3.5" />
                </div>
                <span>Real-world Practice</span>
              </div>
              <div className="flex items-center gap-2 text-slate-300 text-xs sm:text-sm font-medium">
                <div className="w-7 h-7 rounded-lg bg-amber-500/15 border border-amber-500/30 flex items-center justify-center text-amber-400 shrink-0">
                  <Bot className="w-3.5 h-3.5" />
                </div>
                <span>AI Speaking Tutor</span>
              </div>
              <div className="flex items-center gap-2 text-slate-300 text-xs sm:text-sm font-medium">
                <div className="w-7 h-7 rounded-lg bg-amber-500/15 border border-amber-500/30 flex items-center justify-center text-amber-400 shrink-0">
                  <BarChart className="w-3.5 h-3.5" />
                </div>
                <span>Track Your Progress</span>
              </div>
            </div>

            {/* CTA Buttons */}
            <div className="flex flex-col sm:flex-row items-center justify-center lg:justify-start gap-4 pt-4">
              <button
                onClick={onStartLearning}
                className="w-full sm:w-auto px-8 py-4 text-base font-bold text-slate-950 bg-gradient-to-r from-amber-400 via-yellow-400 to-amber-500 hover:from-amber-300 hover:to-amber-400 rounded-2xl shadow-xl shadow-amber-500/25 hover:shadow-amber-500/40 hover:-translate-y-0.5 transition-all flex items-center justify-center gap-2"
              >
                <span>Start Learning Now</span>
                <ArrowRight className="w-5 h-5" />
              </button>

              <button
                onClick={onWatchDemo}
                className="w-full sm:w-auto px-6 py-4 text-base font-semibold text-slate-200 hover:text-white bg-slate-900/80 hover:bg-slate-850 border border-slate-700/80 rounded-2xl transition-all flex items-center justify-center gap-2.5 group"
              >
                <div className="w-7 h-7 rounded-full bg-slate-800 border border-slate-600 flex items-center justify-center group-hover:scale-110 transition-transform text-amber-400">
                  <Play className="w-3.5 h-3.5 fill-current" />
                </div>
                <span>Watch Demo (2 min)</span>
              </button>
            </div>

            {/* Trust Micro-Bullets */}
            <div className="flex flex-wrap items-center justify-center lg:justify-start gap-6 pt-2 text-xs text-slate-400">
              <div className="flex items-center gap-1.5">
                <CheckCircle className="w-4 h-4 text-emerald-400" />
                <span>Self-paced</span>
              </div>
              <div className="flex items-center gap-1.5">
                <CheckCircle className="w-4 h-4 text-emerald-400" />
                <span>Mobile & Web Friendly</span>
              </div>
              <div className="flex items-center gap-1.5">
                <CheckCircle className="w-4 h-4 text-emerald-400" />
                <span>Corporate Industry Relevant</span>
              </div>
            </div>

          </div>

          {/* Right Column: App Mockup + Professional Hero Graphic (matching layout) */}
          <div className="lg:col-span-5 relative flex items-center justify-center">
            
            {/* Visual backdrop card with yellow geometric accent */}
            <div className="relative w-full max-w-[480px] flex items-end justify-center">
              
              {/* Executive Professional Portrait Image */}
              <div className="relative z-10 w-[240px] sm:w-[280px] lg:w-[290px] rounded-3xl overflow-hidden border-2 border-slate-700/60 shadow-2xl mr-[-40px] sm:mr-[-60px] mb-4">
                <img 
                  src="/assets/hero-executive.jpg" 
                  alt="Confident Executive using Elevora" 
                  className="w-full h-auto object-cover object-top hover:scale-105 transition-transform duration-500"
                />
                <div className="absolute inset-0 bg-gradient-to-t from-[#080C16] via-transparent to-transparent opacity-60"></div>
                
                {/* Badge on photo */}
                <div className="absolute bottom-3 left-3 right-3 bg-slate-900/90 backdrop-blur-md p-2 rounded-xl border border-amber-500/30 text-[10px]">
                  <div className="font-bold text-white">Priya Sharma</div>
                  <div className="text-amber-400 text-[9px]">Operations Lead • Elevora Learner</div>
                </div>
              </div>

              {/* The Live Interactive Phone Mockup */}
              <div className="relative z-20">
                <AppPhoneMockup onInteract={onStartLearning} />
              </div>

            </div>

          </div>

        </div>
      </div>
    </section>
  );
};
