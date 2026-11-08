import React from 'react';
import { ArrowRight, Play } from 'lucide-react';

interface CtaBannerSectionProps {
  onStartLearning: () => void;
  onWatchDemo: () => void;
}

export const CtaBannerSection: React.FC<CtaBannerSectionProps> = ({ onStartLearning, onWatchDemo }) => {
  return (
    <section className="py-16 md:py-24 relative overflow-hidden bg-[#0A101E]">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="relative rounded-3xl bg-gradient-to-r from-[#111A2E] via-[#16223D] to-[#101828] border border-amber-500/30 p-8 sm:p-14 text-center lg:text-left flex flex-col lg:flex-row items-center justify-between gap-8 shadow-2xl">
          
          <div className="space-y-3 max-w-xl">
            <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display leading-tight">
              Ready to Speak English <br />
              <span className="text-amber-400">with Confidence?</span>
            </h2>
            <p className="text-sm sm:text-base text-slate-300">
              Join Elevora today and build the workplace English skills you need for your career growth.
            </p>
          </div>

          <div className="flex flex-col sm:flex-row items-center gap-4 shrink-0">
            <button
              onClick={onStartLearning}
              className="w-full sm:w-auto px-8 py-4 text-sm sm:text-base font-bold text-slate-950 bg-gradient-to-r from-amber-400 via-yellow-400 to-amber-500 hover:from-amber-300 hover:to-amber-400 rounded-xl shadow-xl shadow-amber-500/25 flex items-center justify-center gap-2 transition-all"
            >
              <span>Get Started Now</span>
              <ArrowRight className="w-5 h-5" />
            </button>

            <button
              onClick={onWatchDemo}
              className="w-full sm:w-auto px-6 py-4 text-sm sm:text-base font-semibold text-slate-200 hover:text-white bg-slate-900/80 hover:bg-slate-800 border border-slate-700 rounded-xl flex items-center justify-center gap-2 transition-all"
            >
              <Play className="w-4 h-4 fill-current text-amber-400" />
              <span>Watch Demo</span>
            </button>
          </div>

        </div>
      </div>
    </section>
  );
};
