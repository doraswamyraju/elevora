import React from 'react';
import { 
  Play, 
  Sparkles, 
  CheckCircle2, 
  Mic, 
  BookOpen, 
  Award, 
  BarChart3, 
  ArrowRight,
  TrendingUp,
  Volume2
} from 'lucide-react';

interface AppPhoneMockupProps {
  onInteract?: () => void;
}

export const AppPhoneMockup: React.FC<AppPhoneMockupProps> = ({ onInteract }) => {
  return (
    <div className="relative mx-auto w-full max-w-[290px] sm:max-w-[310px] select-none group">
      {/* Outer Phone Bezel with Gold Gradient Rim */}
      <div className="relative rounded-[44px] p-[10px] bg-gradient-to-b from-slate-700 via-slate-900 to-black shadow-2xl shadow-amber-500/10 border-2 border-slate-700/60 ring-1 ring-amber-500/20">
        {/* Speaker & Dynamic Island Notch */}
        <div className="absolute top-4 left-1/2 -translate-x-1/2 w-28 h-4 bg-black rounded-full z-20 flex items-center justify-center gap-2">
          <div className="w-10 h-1 bg-slate-800 rounded-full"></div>
          <div className="w-2.5 h-2.5 bg-slate-900 rounded-full border border-slate-700"></div>
        </div>

        {/* Screen Glass */}
        <div className="relative rounded-[36px] overflow-hidden bg-[#0A0F1D] text-white pt-8 pb-4 px-4 flex flex-col justify-between border border-slate-800/80">
          
          {/* Top Status & Header */}
          <div className="space-y-3">
            {/* Top Bar with Elevora Brand */}
            <div className="flex items-center justify-between pt-1">
              <div className="flex items-center gap-1.5">
                <div className="w-5 h-5 rounded-md bg-amber-400 flex items-center justify-center">
                  <span className="text-[11px] font-black text-slate-950">E</span>
                </div>
                <span className="font-extrabold text-sm tracking-tight text-white font-display">Elevora</span>
              </div>
              <div className="flex items-center gap-1 text-[10px] text-amber-300 bg-amber-500/10 px-2 py-0.5 rounded-full border border-amber-500/20">
                <Sparkles className="w-3 h-3" />
                <span>AI Live</span>
              </div>
            </div>

            {/* User Greeting & Streak */}
            <div className="flex items-center justify-between bg-slate-900/60 p-2.5 rounded-2xl border border-slate-800">
              <div className="flex items-center gap-2">
                <div className="w-8 h-8 rounded-full bg-gradient-to-tr from-amber-500 to-yellow-300 p-0.5">
                  <div className="w-full h-full rounded-full bg-slate-950 flex items-center justify-center font-bold text-amber-400 text-xs">
                    R
                  </div>
                </div>
                <div>
                  <div className="text-[11px] font-bold text-white flex items-center gap-1">
                    Hi Raju <span className="text-amber-400">👋</span>
                  </div>
                  <div className="text-[9px] text-slate-400">Let's continue your learning</div>
                </div>
              </div>
              <div className="text-right">
                <div className="text-[11px] font-extrabold text-amber-400 flex items-center gap-0.5 justify-end">
                  🔥 4 Days
                </div>
                <div className="text-[9px] text-slate-500">Streak</div>
              </div>
            </div>

            {/* Active Course Card (Module 1) */}
            <div className="bg-gradient-to-br from-slate-900 via-[#121B30] to-slate-900 p-3 rounded-2xl border border-amber-500/30 shadow-lg relative overflow-hidden">
              <div className="absolute -right-6 -bottom-6 w-24 h-24 bg-amber-500/10 rounded-full blur-xl pointer-events-none"></div>

              <div className="flex items-center justify-between text-[10px] font-bold text-amber-400 mb-1">
                <span>MODULE 1 • FOUNDATIONS</span>
                <span className="bg-amber-400/20 text-amber-300 px-1.5 py-0.5 rounded text-[9px]">Week 1 of 4</span>
              </div>

              <h4 className="text-xs font-bold text-white leading-tight mb-2">
                Managerial English: Professional Introductions
              </h4>

              {/* Progress Bar */}
              <div className="space-y-1 mb-3">
                <div className="flex justify-between text-[9px] text-slate-400">
                  <span>Day 1 Progress</span>
                  <span className="text-amber-400 font-semibold">85% Complete</span>
                </div>
                <div className="w-full h-1.5 bg-slate-800 rounded-full overflow-hidden">
                  <div className="h-full bg-gradient-to-r from-amber-400 to-yellow-300 w-[85%] rounded-full"></div>
                </div>
              </div>

              {/* Continue Button */}
              <button 
                onClick={onInteract}
                className="w-full py-2 bg-gradient-to-r from-amber-400 to-amber-500 text-slate-950 font-bold text-[11px] rounded-xl flex items-center justify-center gap-1.5 shadow-md hover:scale-[1.02] transition-transform"
              >
                <Play className="w-3.5 h-3.5 fill-current" />
                Continue Day 1 Role-Play
              </button>
            </div>

            {/* Next Activity Preview */}
            <div className="bg-slate-900/50 p-2.5 rounded-xl border border-slate-800 flex items-center justify-between">
              <div className="flex items-center gap-2">
                <div className="w-7 h-7 rounded-lg bg-blue-500/10 border border-blue-500/30 flex items-center justify-center text-blue-400">
                  <Mic className="w-3.5 h-3.5" />
                </div>
                <div>
                  <div className="text-[10px] font-bold text-white">AI Speaking Tutor</div>
                  <div className="text-[9px] text-slate-400">Scenario: Cross-functional Sync</div>
                </div>
              </div>
              <div className="text-[9px] font-semibold text-emerald-400 bg-emerald-500/10 px-2 py-0.5 rounded-full border border-emerald-500/20">
                Score: 92%
              </div>
            </div>
          </div>

          {/* Bottom App Navigation Mockup */}
          <div className="mt-4 pt-2 border-t border-slate-800/80 flex items-center justify-around text-slate-500">
            <div className="flex flex-col items-center text-amber-400">
              <BookOpen className="w-4 h-4" />
              <span className="text-[8px] font-bold mt-0.5">Learn</span>
            </div>
            <div className="flex flex-col items-center hover:text-slate-300">
              <Mic className="w-4 h-4" />
              <span className="text-[8px] mt-0.5">AI Tutor</span>
            </div>
            <div className="flex flex-col items-center hover:text-slate-300">
              <BarChart3 className="w-4 h-4" />
              <span className="text-[8px] mt-0.5">Progress</span>
            </div>
            <div className="flex flex-col items-center hover:text-slate-300">
              <Award className="w-4 h-4" />
              <span className="text-[8px] mt-0.5">Badges</span>
            </div>
          </div>

        </div>
      </div>

      {/* Floating Accent Badges (matching reference image style) */}
      <div className="absolute -bottom-4 -left-6 bg-slate-900/90 backdrop-blur-md border border-slate-700/80 p-2.5 rounded-2xl shadow-xl flex items-center gap-2.5 z-20">
        <div className="w-8 h-8 rounded-xl bg-emerald-500/20 border border-emerald-500/30 flex items-center justify-center text-emerald-400">
          <TrendingUp className="w-4 h-4" />
        </div>
        <div>
          <div className="text-[11px] font-bold text-white">+48% Fluency Gain</div>
          <div className="text-[9px] text-slate-400">In 4 Weeks with AI Tutor</div>
        </div>
      </div>

      <div className="absolute -top-3 -right-5 bg-slate-900/90 backdrop-blur-md border border-amber-500/40 p-2.5 rounded-2xl shadow-xl flex items-center gap-2 z-20">
        <div className="w-7 h-7 rounded-lg bg-amber-400/20 flex items-center justify-center text-amber-400">
          <Sparkles className="w-4 h-4" />
        </div>
        <div className="text-[10px] font-bold text-amber-300">
          Role-Play with AI
        </div>
      </div>
    </div>
  );
};
