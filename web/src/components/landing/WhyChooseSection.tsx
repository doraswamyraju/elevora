import React from 'react';
import { 
  Zap, 
  Bot, 
  Layers, 
  TrendingUp, 
  CheckCircle2 
} from 'lucide-react';

export const WhyChooseSection: React.FC = () => {
  const pillars = [
    {
      icon: Zap,
      title: "Practical & Job-Ready",
      desc: "Learn English you can use at work from Day 1. No theoretical grammar drills — only situational business communication."
    },
    {
      icon: Bot,
      title: "AI-Powered Learning",
      desc: "Practice speaking anytime with your 24/7 AI Tutor. Receive instant sentence refinement, fluency scoring and confidence building."
    },
    {
      icon: Layers,
      title: "Structured & Simple",
      desc: "A clear, step-by-step learning path across 4 comprehensive modules, broken down into actionable daily lessons."
    },
    {
      icon: TrendingUp,
      title: "Track & Grow",
      desc: "Measure your actual progress and build tangible corporate confidence with real-time feedback and weekly milestones."
    }
  ];

  return (
    <section className="py-20 md:py-32 relative overflow-hidden bg-[#080C16]">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 lg:gap-16 items-center">
          
          {/* Left Column: Real photo collage from user's reference */}
          <div className="lg:col-span-5 relative">
            <div className="relative mx-auto max-w-[420px] rounded-3xl overflow-hidden shadow-2xl border-2 border-slate-700/60 group">
              <img 
                src="/assets/why-choose-collage.jpg" 
                alt="Elevora Professionals Collaborating" 
                className="w-full h-auto object-cover group-hover:scale-105 transition-transform duration-700"
              />
              <div className="absolute inset-0 bg-gradient-to-t from-[#080C16]/80 via-transparent to-transparent"></div>
              
              {/* Floating pill badge */}
              <div className="absolute bottom-4 left-4 right-4 bg-slate-900/90 backdrop-blur-md p-3 rounded-2xl border border-slate-700/80 flex items-center justify-between">
                <div>
                  <div className="text-xs font-bold text-white">Managerial English Focus</div>
                  <div className="text-[10px] text-amber-400">Tested in 100+ Real Workplace Scenarios</div>
                </div>
                <div className="w-8 h-8 rounded-full bg-amber-400/20 text-amber-400 flex items-center justify-center font-bold text-xs">
                  4.9★
                </div>
              </div>
            </div>
          </div>

          {/* Right Column: Why Choose Elevora details */}
          <div className="lg:col-span-7 space-y-8">
            <div>
              <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display leading-tight">
                Why Choose <span className="text-amber-400">Elevora?</span>
              </h2>
              <p className="mt-3 text-base sm:text-lg text-slate-300">
                More than just English lessons, Elevora helps you communicate, collaborate, and grow in your career with executive presence.
              </p>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
              {pillars.map((pillar, idx) => {
                const Icon = pillar.icon;
                return (
                  <div 
                    key={idx}
                    className="p-5 rounded-2xl bg-[#0E172B] border border-slate-800 hover:border-amber-500/40 transition-all hover:-translate-y-1 group"
                  >
                    <div className="w-10 h-10 rounded-xl bg-amber-500/10 border border-amber-500/20 flex items-center justify-center text-amber-400 group-hover:scale-110 transition-transform mb-3.5">
                      <Icon className="w-5 h-5" />
                    </div>
                    <h3 className="text-base font-bold text-white mb-1.5">
                      {pillar.title}
                    </h3>
                    <p className="text-xs text-slate-400 leading-relaxed">
                      {pillar.desc}
                    </p>
                  </div>
                );
              })}
            </div>

            {/* Quick check list */}
            <div className="pt-2 flex flex-wrap gap-4 text-xs font-semibold text-slate-300">
              <span className="flex items-center gap-1.5">
                <CheckCircle2 className="w-4 h-4 text-amber-400" />
                Designed with Corporate HRs & Trainers
              </span>
              <span className="flex items-center gap-1.5">
                <CheckCircle2 className="w-4 h-4 text-amber-400" />
                Daily 15-Minute Micro-Learning
              </span>
            </div>
          </div>

        </div>
      </div>
    </section>
  );
};
